package net.ddns.victorlundgren.sudokusolver.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Solver {

    public boolean isLegalBoard(Board board) {
        return checkSuduku(board.getCells()).isEmpty();
    }

    public void solveSudoku(Board board) {
        ArrayList<Integer> cells = board.getCells();
        Random random = new Random();
        if (isLegalBoard(board)) {
            ArrayList<Integer> emptyCells = getEmptyCellIndexes(cells);
//            for (Integer i : emptyCells) {
//                cells.set(i, random.nextInt(9) + 1);
//            }
//            Collections.shuffle(emptyCells);
//            while (!isLegalBoard(board)) {
//                getBoard(board, cells, emptyCells);
//
//            }
            test(cells);
        } else {
            throw new IllegalArgumentException("Illegal board");
        }
    }

    private boolean test(ArrayList<Integer> cells) {
        int index = -1;
        boolean isEmpty = true;
        for (int i = 0; i < Board.ROWS*Board.COLUMNS; i++) {
            if (cells.get(i) == 0) {
                index = i;

                // We still have some remaining
                // missing values in Sudoku
                isEmpty = false;
                break;
            }
        }

        // No empty space left
        if (isEmpty) {
            return true;
        }

        // Else for each-row backtrack
        for (int num = 1; num <= 9; num++) {
            if (checkAll(cells, index, num)) {
                cells.set(index, num);
                if (test(cells)) {
                    // print(board, n);
                    return true;
                } else {
                    // replace it
                    cells.set(index, 0);
                }
            }
        }
        return false;
    }

    private boolean isSafe(int[][] board,
                                 int row, int col,
                                 int num)
    {
        // Row has the unique (row-clash)
        for (int d = 0; d < board.length; d++)
        {

            // Check if the number we are trying to
            // place is already present in
            // that row, return false;
            if (board[row][d] == num) {
                return false;
            }
        }

        // Column has the unique numbers (column-clash)
        for (int r = 0; r < board.length; r++)
        {

            // Check if the number
            // we are trying to
            // place is already present in
            // that column, return false;
            if (board[r][col] == num)
            {
                return false;
            }
        }

        // Corresponding square has
        // unique number (box-clash)
        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++)
        {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++)
            {
                if (board[r][d] == num)
                {
                    return false;
                }
            }
        }

        // if there is no clash, it's safe
        return true;
    }

    public boolean solveSudoku(
            int[][] board, int n)
    {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] == 0)
                {
                    row = i;
                    col = j;

                    // We still have some remaining
                    // missing values in Sudoku
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // No empty space left
        if (isEmpty)
        {
            return true;
        }

        // Else for each-row backtrack
        for (int num = 1; num <= n; num++)
        {
            if (isSafe(board, row, col, num))
            {
                board[row][col] = num;
                if (solveSudoku(board, n))
                {
                    // print(board, n);
                    return true;
                }
                else
                {
                    // replace it
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    private void getBoard(Board board, ArrayList<Integer> cells, ArrayList<Integer> emptyCells) {
        int savedCellNumber = 0;
        int savedError = 0;
        ListIterator<Integer> iterator = emptyCells.listIterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if(isLegalBoard(board)) {
                break;
            }
            savedCellNumber = cells.get(i);
            savedError = checkSuduku(cells).size();
            if(!checkAll(cells, i, cells.get(i))) {
                cells.set(i, cells.get(i) < 9 ? cells.get(i) + 1 : 1);
                while (savedCellNumber != cells.get(i) && checkSuduku(cells).size() >= savedError) {
                    if(isLegalBoard(board)) {
                        return;
                    }
                    cells.set(i, cells.get(i) < 9 ? cells.get(i) + 1 : 1);
                    Log.d("MyTag", "solveSudoku: i:" + i + " temp:" + cells.get(i) + " savedNumber:" + savedCellNumber + " errors:" + checkSuduku(cells).size() + " savedError:" + savedError);
                }
            } else {
                iterator.remove();
            }
        }
    }

    private ArrayList<Integer> getEmptyCellIndexes(ArrayList<Integer> cells) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i) == 0) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    private HashSet<Integer> checkSuduku(ArrayList<Integer> cells) {
        HashSet<Integer> errorCells = new HashSet<>(checkRows(cells));
        errorCells.addAll(checkColumns(cells));
        errorCells.addAll(checkBlocks(cells));
        return errorCells;
    }

    public boolean checkAll(ArrayList<Integer> cells, int index, int num) {
        return checkRow(cells, index, num) && checkColumn(cells, index, num) &&
                checkBlock(cells, index, num);
    }

    private boolean checkRow(ArrayList<Integer> cells, int index, int num) {
        int row = index / Board.ROWS;
        for (int col = 0; col < Board.COLUMNS; col++) {
            int i = row + col;
            if(cells.get(i) != 0) {
                if (i != index && cells.get(i).equals(num)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkColumn(ArrayList<Integer> cells, int index, int num) {
        int col = index % Board.COLUMNS;
        for (int row = 0; row < Board.COLUMNS; row++) {
            int i = col + row * Board.ROWS;
            if(cells.get(i) != 0) {
                if (i != index && cells.get(i).equals(num)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkBlock(ArrayList<Integer> cells, int index, int num) {
        int startRow = (index / Board.ROWS) / 3;
        int startCol = (index % Board.COLUMNS) / 3;
        for (int row = startRow*3; row < startRow*3+3; row++) {
            for (int col = startCol*3; col < startCol*3+3; col++) {
                int i = col + row * Board.ROWS;
                if(cells.get(i) != 0) {
                    if (i != index && cells.get(i).equals(num)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private ArrayList<Integer> checkRows(ArrayList<Integer> cells) {
        ArrayList<Integer> check = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> errors = new ArrayList<>();
        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLUMNS; j++) {
                int index = i * Board.ROWS + j;
                Integer cell = cells.get(index);
                if(!checkCell(cell, check)) {
                    errors.add(index);
                }
            }
            check = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        return errors;
    }

    private ArrayList<Integer> checkColumns(ArrayList<Integer> cells) {
        ArrayList<Integer> check = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> errors = new ArrayList<>();
        for (int i = 0; i < Board.COLUMNS; i++) {
            for (int j = 0; j < Board.ROWS; j++) {
                int index = i + j * Board.ROWS;
                Integer cell = cells.get(index);
                if(!checkCell(cell, check)) {
                    errors.add(index);
                }
            }
            check = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        return errors;
    }

    private ArrayList<Integer> checkBlocks(ArrayList<Integer> cells) {
        ArrayList<Integer> check1 = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> check2 = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> check3 = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> errors = new ArrayList<>();
        for (int i = 0; i < Board.ROWS; i++) {
            if(i % 3 == 0) {
                check1 = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
                check2 = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
                check3 = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
            }
            for (int j = 0; j < Board.COLUMNS; j++) {
                int index = i * Board.ROWS + j;
                Integer cell = cells.get(index);
                if (j < 3) {
                    if(!checkCell(cell, check1)) {
                        errors.add(index);
                    }
                } else if (j < 6) {
                    if(!checkCell(cell, check2)) {
                        errors.add(index);
                    }
                } else {
                    if(!checkCell(cell, check3)) {
                        errors.add(index);
                    }
                }
            }
        }
        return errors;
    }

    private boolean checkCell(Integer cell, ArrayList<Integer> check) {
        if(check.contains(cell)) {
            check.remove(cell);
        } else return cell == 0;
        return true;
    }
}

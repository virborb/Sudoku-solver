package net.ddns.victorlundgren.sudokusolver.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class Solver {

    public boolean isLegalBoard(Board board) {
        return checkSuduku(board.getCells()) == 0;
    }

    public Board solveSudoku(Board board) {
        ArrayList<Integer> cells = board.getCells();
        if (isLegalBoard(board)) {
            ListIterator<Integer> indexes = getEmptyCellIndexes(cells).listIterator();
            while (indexes.hasNext()) {
                Integer i = indexes.next();
            }
        } else {
            throw new IllegalArgumentException("Illegal board");
        }
        return board;
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

    private int checkSuduku(ArrayList<Integer> cells) {
        return checkRows(cells) + checkColumns(cells) + checkBlocks(cells);
    }

    private int checkRows(ArrayList<Integer> cells) {
        ArrayList<Integer> check = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int errors = 0;
        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLUMNS; j++) {
                Integer cell = cells.get(i * Board.ROWS + j);
                if(!checkCell(cell, check)) {
                    errors++;
                }
            }
            check = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        return errors;
    }

    private int checkColumns(ArrayList<Integer> cells) {
        ArrayList<Integer> check = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int errors = 0;
        for (int i = 0; i < Board.COLUMNS; i++) {
            for (int j = 0; j < Board.ROWS; j++) {
                Integer cell = cells.get(i+j*Board.ROWS);
                if(!checkCell(cell, check)) {
                    errors++;
                }
            }
            check = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        return errors;
    }

    private int checkBlocks(ArrayList<Integer> cells) {
        ArrayList<Integer> check1 = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> check2 = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> check3 = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int errors = 0;
        for (int i = 0; i < Board.ROWS; i++) {
            if(i % 3 == 0) {
                check1 = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
                check2 = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
                check3 = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
            }
            for (int j = 0; j < Board.COLUMNS; j++) {
                Integer cell = cells.get(i * Board.ROWS + j);
                if (j < 3) {
                    if(!checkCell(cell, check1)) {
                        errors++;
                    }
                } else if (j < 6) {
                    if(!checkCell(cell, check2)) {
                        errors++;
                    }
                } else {
                    if(!checkCell(cell, check3)) {
                        errors++;
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

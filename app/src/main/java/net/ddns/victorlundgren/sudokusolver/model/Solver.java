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

    public Board solveSudoku(Board board) {
        ArrayList<Integer> cells = board.getCells();
        Random random = new Random();
        if (isLegalBoard(board)) {
            ArrayList<Integer> emptyCells = getEmptyCellIndexes(cells);
            for (Integer i : emptyCells) {
                cells.set(i, random.nextInt(9) + 1);
            }
            Collections.shuffle(emptyCells);
            int savedCellNumber = 0;
            int savedError = 0;
            for (Integer i : emptyCells) {
                if(isLegalBoard(board)) {
                    break;
                }
                savedCellNumber = cells.get(i);
                savedError = checkSuduku(cells).size();
                while (savedCellNumber != cells.get(i) && checkSuduku(cells).size() >= savedError) {
                    if(checkSuduku(cells).isEmpty()) {
                        return board;
                    }
                    cells.set(i, cells.get(i) < 9 ? cells.get(i) + 1 : 1);
                    Log.d("MyTag", "solveSudoku: i:" + i + " temp:" + cells.get(i) + " savedNumber:" + savedCellNumber + " errors:" + checkSuduku(cells) + " savedError:" + savedError);
                }
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

    private HashSet<Integer> checkSuduku(ArrayList<Integer> cells) {
        HashSet<Integer> errorCells = new HashSet<>(checkRows(cells));
        errorCells.addAll(checkColumns(cells));
        errorCells.addAll(checkBlocks(cells));
        return errorCells;
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

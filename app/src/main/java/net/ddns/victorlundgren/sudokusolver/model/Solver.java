package net.ddns.victorlundgren.sudokusolver.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class Solver {
    private Board board;

    public Solver(Board board) {
        this.board = board;
    }

    public boolean isLegalBoard() {
        return false;
    }

    public Board solveSudoku() {
        ArrayList<Integer> cells = board.getCells();
        Log.d("MyTag", "checkRows return: " + checkRows(cells));
        Log.d("MyTag", "checkColumns return: " + checkColumns(cells));
        Log.d("MyTag", "checkBlocks return: " + checkBlocks(cells));
        return board;
    }

    private boolean checkSuduku(ArrayList<Integer> cells) {
        if(!checkRows(cells)) {
            return false;
        }
        return true;
    }

    private boolean checkRows(ArrayList<Integer> cells) {
        ArrayList<Integer> check = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLUMNS; j++) {
                Integer cell = cells.get(i * Board.ROWS + j);
                if(!checkCell(cell, check)) {
                    return false;
                }
            }
            check = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        return true;
    }

    private boolean checkColumns(ArrayList<Integer> cells) {
        ArrayList<Integer> check = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < Board.COLUMNS; i++) {
            for (int j = 0; j < Board.ROWS; j++) {
                Integer cell = cells.get(i+j*Board.ROWS);
                if(!checkCell(cell, check)) {
                    return false;
                }
            }
            check = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        return true;
    }

    private boolean checkBlocks(ArrayList<Integer> cells) {
        ArrayList<Integer> check1 = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> check2 = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> check3 = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < Board.ROWS; i++) {
            if(i % 3 == 0) {
                check1 = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
                check2 = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
                check3 = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
            }
            for (int j = 0; j < Board.COLUMNS; j++) {
                Integer cell = cells.get(i * Board.ROWS + j);
                Log.d("MyTag", "checkBlocks: index:" + (i * Board.ROWS + j) + " temp: " + cell);
                if (j < 3) {
                    if(!checkCell(cell, check1)) {
                        return false;
                    }
                } else if (j < 6) {
                    if(!checkCell(cell, check2)) {
                        return false;
                    }
                } else {
                    if(!checkCell(cell, check3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkCell(Integer cell, ArrayList<Integer> check) {
        if(check.contains(cell)) {
            check.remove(cell);
        } else return cell == 0;
        return true;
    }
}

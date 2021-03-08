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
        for (int i = 0; i < 9; i++) {
            Log.d("MyTag", "solveSudoku: " + cells.subList(i * Board.ROWS, i * Board.ROWS + 9).toString());
            Log.d("MyTag", "solveSudoku: " + checkRow(new ArrayList<Integer>(cells.subList(i * Board.ROWS, i * Board.ROWS + 9))));
        }
        Log.d("MyTag", "checkColumns return: " + checkColumns(cells));
        return board;
    }

    private boolean checkSuduku(ArrayList<Integer> cells) {
        if(!checkRow(cells)) {
            return false;
        }
        return true;
    }

    private boolean checkRow(ArrayList<Integer> cells) {
        ArrayList<Integer> check =
                new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < 9; i++) {
            Integer temp = cells.get(i);
            if(check.contains(temp)) {
                check.remove(temp);
            } else if (temp != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumns(ArrayList<Integer> cells) {
        ArrayList<Integer> check =
                new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < Board.COLUMNS; i++) {
            for (int j = 0; j < Board.ROWS; j++) {
                Integer temp = cells.get(i+j*Board.ROWS);
                if(check.contains(temp)) {
                    check.remove(temp);
                } else if (temp != 0) {
                    return false;
                }
            }
            check = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        return true;
    }

    private boolean checkRows(ArrayList<Integer> cells) {

        ArrayList<Integer> check =
                new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < cells.size()-1; i++) {
            if(i != 0 && i%22 == 0) {
                i = i - 19;
                check = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
            } else {
                i = (i != 0 && i % 3 == 0) ? i + 6 : i;
            }
            Integer temp = cells.get(i);
            Log.d("MyTag", "checkRow: " + temp + " i: " + i);
            if(check.contains(temp)) {
                check.remove(temp);
            } else if (temp != 0) {
                return false;
            }
        }
        return true;
    }
}

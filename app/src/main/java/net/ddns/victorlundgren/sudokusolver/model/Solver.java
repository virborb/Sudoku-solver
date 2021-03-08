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
        return board;
    }

    private boolean checkSuduku(ArrayList<Integer> cells) {
        if(!checkRows(cells)) {
            return false;
        }
        return true;
    }

    private boolean checkRows(ArrayList<Integer> cells) {
        ArrayList<Integer> check =
                new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLUMNS; j++) {
                Integer temp = cells.get(i * Board.ROWS + j);
                if (check.contains(temp)) {
                    check.remove(temp);
                } else if (temp != 0) {
                    return false;
                }
            }
            check = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
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

//    private boolean checkRows(ArrayList<Integer> cells) {
//
//        ArrayList<Integer> check =
//                new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
//        for (int i = 0; i < cells.size()-1; i++) {
//            if(i != 0 && i%22 == 0) {
//                i = i - 19;
//                check = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
//            } else {
//                i = (i != 0 && i % 3 == 0) ? i + 6 : i;
//            }
//            Integer temp = cells.get(i);
//            Log.d("MyTag", "checkRow: " + temp + " i: " + i);
//            if(check.contains(temp)) {
//                check.remove(temp);
//            } else if (temp != 0) {
//                return false;
//            }
//        }
//        return true;
//    }
}

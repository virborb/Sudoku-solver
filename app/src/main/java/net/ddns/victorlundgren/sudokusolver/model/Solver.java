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
            checkCellBlock(new ArrayList<Integer>(cells.subList(i * Board.ROWS, i * Board.ROWS + 9)));
        }
        return board;
    }

    private boolean checkSuduku(ArrayList<Integer> cells) {
        if(!checkCellBlock(cells)) {
            return false;
        }
        return true;
    }

    private boolean checkCellBlock(ArrayList<Integer> cells) {
        ArrayList<Integer> check =
                new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
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

    private boolean checkRow(ArrayList<Integer> cells) {
        ArrayList<Integer> check =
                new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
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
}

package net.ddns.victorlundgren.sudokusolver.model;

public class Solver {
    private Board board;

    public Solver(Board board) {
        this.board = board;
    }

    public boolean isLegalBoard() {
        return false;
    }

    public Board solvSudoku() {
        if(isLegalBoard()) {
            int[] cells = board.getCells();
            for (int cell : cells) {

            }
        }
        return board;
    }
}

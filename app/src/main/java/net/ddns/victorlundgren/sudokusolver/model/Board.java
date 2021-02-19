package net.ddns.victorlundgren.sudokusolver.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private static final int ROWS = 9;
    private int[] cells;

    public Board(int[] cells) {
        this.cells = cells;
    }

    public int[] getCells() {
        return cells;
    }

    public int getCell(Position p) {
        return cells[p.getX()*ROWS + p.getY()];
    }

    public void setCells(int cell, Position p) {
        cells[p.getX()*ROWS + p.getY()] = cell;
    }
}

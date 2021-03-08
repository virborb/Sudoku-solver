package net.ddns.victorlundgren.sudokusolver.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    protected static final int ROWS = 9;
    protected static final int COLUMNS = 9;
    protected static final int BLOCK = 9;
    private ArrayList<Integer> cells;

    public Board(ArrayList<Integer> cells) {
        this.cells = cells;
    }

    public ArrayList<Integer> getCells() {
        return cells;
    }

    public int getCell(Position p) {
        return cells.get(p.getX() * ROWS + p.getY());
    }

    public void setCells(int cell, Position p) {
        cells.set(p.getX() * ROWS + p.getY(), cell);
    }
}

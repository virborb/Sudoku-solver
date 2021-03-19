package net.ddns.victorlundgren.sudokusolver.model;

import junit.framework.TestCase;

import java.util.ArrayList;

public class SolverTest extends TestCase {
    private Board board;
    private Solver solver;
    private ArrayList<Integer> cells;

    @Override
    public void setUp() throws Exception {
        cells = new ArrayList<>();
        for (int i = 0; i < Board.ROWS*Board.COLUMNS; i++) {
            cells.add(0);
        }
        board = new Board(cells);
        solver = new Solver();
    }

    public void testIsLegalBoard() {
    }

    public void testSolveSudoku() {
    }

    public void testLegalRow() {
        for (int i = 0; i < 9; i++) {
            cells.set(i, i + 1);
        }
        for (int i = 0; i < 9; i++) {
            assertTrue(solver.checkAll(cells, i, i+1));
        }
        assertTrue(solver.isLegalBoard(board));
    }

    public void testIllegalRow() {
        for (int i = 0; i < 9; i++) {
            cells.set(i, i + 1);
        }
        cells.set(0, 6);
        assertFalse(solver.checkAll(cells, 0, 6));
        assertFalse(solver.isLegalBoard(board));
    }

    public void testLegalColumn() {
        for (int i = 0; i < 9; i++) {
            cells.set(i*Board.ROWS, i + 1);
        }
        for (int i = 0; i < 9; i++) {
            assertTrue(solver.checkAll(cells, i*Board.ROWS, i+1));
        }
        assertTrue(solver.isLegalBoard(board));
    }

    public void testIllegalColumn() {
        for (int i = 0; i < 9; i++) {
            cells.set(i*Board.ROWS+2, i + 1);
        }
        cells.set(2, 6);
        assertFalse(solver.checkAll(cells, 2, 6));
        assertFalse(solver.isLegalBoard(board));
    }

    public void testLegalBlock() {
        cells.set( 30, 1);
        cells.set( 31, 2);
        cells.set( 32, 3);

        cells.set( 39, 4);
        cells.set( 40, 5);
        cells.set( 41, 6);

        cells.set( 48, 7);
        cells.set( 49, 8);
        cells.set( 50, 9);
        int num = 1;
        for (int i = 30; i < 33; i++) {
            assertTrue(solver.checkAll(cells, i, num));
            num++;
        }
        for (int i = 39; i < 42; i++) {
            assertTrue(solver.checkAll(cells, i, num));
            num++;
        }
        for (int i = 48; i < 51; i++) {
            assertTrue(solver.checkAll(cells, i, num));
            num++;
        }
        assertTrue(solver.isLegalBoard(board));
    }

    public void testIllegalBlock() {
        cells.set( 0, 6);
        cells.set( 1, 2);
        cells.set( 2, 3);

        cells.set( 9, 4);
        cells.set( 10, 5);
        cells.set( 11, 6);

        cells.set( 18, 7);
        cells.set( 19, 8);
        cells.set( 20, 9);
        assertFalse(solver.checkAll(cells, 0, 6));
        assertFalse(solver.isLegalBoard(board));
    }
}
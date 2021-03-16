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
            assertTrue(solver.checkAll(cells, i));
        }
        assertTrue(solver.isLegalBoard(board));
    }

    public void testIllegalRow() {
        for (int i = 0; i < 9; i++) {
            cells.set(i, i + 1);
        }
        cells.set(0, 6);
        assertFalse(solver.checkAll(cells, 0));
        assertFalse(solver.isLegalBoard(board));
    }

    public void testLegalColumn() {
        for (int i = 0; i < 9; i++) {
            cells.set(i*Board.ROWS, i + 1);
        }
        for (int i = 0; i < 9; i++) {
            assertTrue(solver.checkAll(cells, i*Board.ROWS));
        }
        assertTrue(solver.isLegalBoard(board));
    }

    public void testIllegalColumn() {
        for (int i = 0; i < 9; i++) {
            cells.set(i*Board.ROWS+2, i + 1);
        }
        cells.set(2, 6);
        assertFalse(solver.checkAll(cells, 2));
        assertFalse(solver.isLegalBoard(board));
    }

    public void testLegalBlock() {
        cells.set( 0, 1);
        cells.set( 1, 2);
        cells.set( 2, 3);

        cells.set( 9, 4);
        cells.set( 10, 5);
        cells.set( 11, 6);

        cells.set( 18, 7);
        cells.set( 19, 8);
        cells.set( 20, 9);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(solver.checkAll(cells, i * Board.ROWS + j));
            }
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
        assertFalse(solver.checkAll(cells, 0));
        assertFalse(solver.isLegalBoard(board));
    }
}
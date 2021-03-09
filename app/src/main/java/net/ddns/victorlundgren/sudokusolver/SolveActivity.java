package net.ddns.victorlundgren.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import net.ddns.victorlundgren.sudokusolver.model.Board;
import net.ddns.victorlundgren.sudokusolver.model.Solver;

import java.util.ArrayList;
import java.util.List;

public class SolveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Integer> cells = getIntent().getIntegerArrayListExtra(InsertActivity.CELL_ARRAY);
        List<Fragment> list = getSupportFragmentManager().getFragments();

        Board board = new Board(cells);
        Solver solver = new Solver();
        try {
            solver.solveSudoku(board);
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "Illegal Suduku board",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        for (Fragment f: list) {
            CellBlockFragment cellBlock = (CellBlockFragment) f;
            cellBlock.setCells(cells);
            cells = new ArrayList<Integer>(cells.subList(9, cells.size()));
        }
    }
}
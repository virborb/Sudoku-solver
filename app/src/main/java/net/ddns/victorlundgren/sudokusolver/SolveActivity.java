package net.ddns.victorlundgren.sudokusolver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

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
        List<Integer> cells = getIntent().getIntegerArrayListExtra(InsertActivity.CELL_ARRAY);
        List<Fragment> list = getSupportFragmentManager().getFragments();
        for (Fragment f: list) {
            CellGroupFragment cellGroup = (CellGroupFragment) f;
            cellGroup.setCells(cells);
            cells = cells.subList(9, cells.size());
        }
    }
}
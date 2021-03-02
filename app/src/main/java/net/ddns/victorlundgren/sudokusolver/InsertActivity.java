package net.ddns.victorlundgren.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class InsertActivity extends AppCompatActivity {
    public static final String CELL_ARRAY = "cells";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(e ->{
            Intent intent = new Intent(this, SolveActivity.class);
            List<Fragment> list = getSupportFragmentManager().getFragments();
            ArrayList<Integer> cells = new ArrayList<>();
            for (Fragment f: list) {
                CellBlockFragment cellBlock = (CellBlockFragment) f;
                cells.addAll(cellBlock.getCells());
            }
            intent.putExtra(CELL_ARRAY, cells);
            startActivity(intent);
        });

    }
}
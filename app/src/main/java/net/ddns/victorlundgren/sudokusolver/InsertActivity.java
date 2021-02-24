package net.ddns.victorlundgren.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        ArrayList<Integer> cells = new ArrayList<>();
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(e ->{
            Intent intent = new Intent(this, SolveActivity.class);
            List<Fragment> list = getSupportFragmentManager().getFragments();
            for (Fragment f: list) {
                CellGroupFragment cellRow = (CellGroupFragment) f;
                cells.addAll(((CellGroupFragment) f).getCells());
            }
            intent.putExtra("cells", cells);
            startActivity(intent);
        });

    }
}
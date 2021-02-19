package net.ddns.victorlundgren.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scan = findViewById(R.id.manual);
        scan.setOnClickListener(e ->{
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        });
    }
}
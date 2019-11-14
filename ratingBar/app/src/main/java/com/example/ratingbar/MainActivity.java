package com.example.ratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RatingBar rb = findViewById(R.id.ratingbar);
        String rating =  String.valueOf(rb.getRating());
        Toast.makeText(MainActivity.this, "Ratting"+rating,Toast.LENGTH_SHORT).show();

        String stepSize  = String.valueOf(rb.getStepSize());
        Toast.makeText(MainActivity.this, "StepSize"+stepSize,Toast.LENGTH_SHORT).show();

        String progress = String.valueOf(rb.getProgress());
        Toast.makeText(MainActivity.this, "Progress"+progress,Toast.LENGTH_SHORT).show();
    }
}

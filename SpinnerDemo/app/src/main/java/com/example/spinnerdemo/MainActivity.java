package com.example.spinnerdemo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mListname;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mSpinner = findViewById(R.id.spinner);
        mListname = new ArrayList<>();
        mListname.add("北京");
        mListname.add("上海");
        mListname.add("天津");
        mListname.add("重庆");
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, R.layout.layout_spinner_display,R.id.txtvwSpinner, mListname);
        myAdapter.setDropDownViewResource(R.layout.layout_dropdown_style);
        mSpinner.setAdapter(myAdapter);

    }

    private void initView() {
        mSpinner = findViewById(R.id.spinner);
    }
}

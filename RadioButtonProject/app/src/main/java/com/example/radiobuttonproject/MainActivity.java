package com.example.radiobuttonproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup)findViewById(R.id.rdg);
        textView = (TextView) findViewById(R.id.tv);

        //利用setOnCheckedChangeListener()为RadioGroup建立监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbtn) {
                    textView.setText("你选的性别是：男");
                } else {
                    textView.setText("你选的性别是：女");
                }
            }
        });

    }
}

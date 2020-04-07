package com.example.programme_33;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/***
 * 实现textView的滚动显示
 */
public class MainActivity extends AppCompatActivity {

    private TextView tv_cycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        tv_cycle.setSelected(true);
    }

    private void initView() {
        tv_cycle = (TextView) findViewById(R.id.tv_cycle);
    }
}

package com.example.buttonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button myBtn1;
    private Button myBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通过findViewById初始化条件
        myBtn1 = (Button)findViewById(R.id.btn_1);
        myBtn2 = (Button)findViewById(R.id.btn_2);

        //匿名内部类的方式实现按钮2的点击事件
        myBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBtn2.setText("按钮2已被点击");
            }
        });
    }

    //通过实现onClick（）方法，实现按钮1的点击事件

    public void click(View v) {
        myBtn1.setText("按钮1已被单击");
    }
}

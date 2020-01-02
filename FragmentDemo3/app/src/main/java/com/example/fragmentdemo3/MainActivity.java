package com.example.fragmentdemo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_first;
    private Button btn_second;
    private Button btn_third;
    private Button btn_fourth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //根据id拿对象
        btn_first = findViewById(R.id.btn_first);
        btn_second = findViewById(R.id.btn_second);
        btn_third = findViewById(R.id.btn_third);
        btn_fourth = findViewById(R.id.btn_fourth);
        //设置事件
        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        btn_third.setOnClickListener(this);
        btn_fourth.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //获得fragment的管理者
        FragmentManager  manager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.btn_first:
                //将主布局里的Relation那块区域替换成fragment
                fragmentTransaction.replace(R.id.rl,new FirstFragment());
                break;
            case R.id.btn_second:
                fragmentTransaction.replace(R.id.rl,new SecondFragment());
                break;
            case R.id.btn_third:
                fragmentTransaction.replace(R.id.rl,new ThirdFragment());
                break;
            case R.id.btn_fourth:
                fragmentTransaction.replace(R.id.rl,new FourthFragment());
                break;
                default:break;
        }
        fragmentTransaction.commit();
    }
}

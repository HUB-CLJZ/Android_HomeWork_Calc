package com.example.fragmentdemo5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

/*
* 本案例演示fragment如何进行通信
**/
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得Fragment的管理者
        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //将fragment和activity关联
        //第三个参数是此fragment的标识
        ft.replace(R.id.ll1,new FirstFragment(),"f1");
        ft.replace(R.id.ll2,new SecondFragment(),"f2");
        //提交事务
        ft.commit();
    }
}

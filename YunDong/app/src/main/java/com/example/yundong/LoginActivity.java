package com.example.yundong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    private EditText mEdtname,mEdtpasswd;
    private Button mBtnlogin,mBtnreg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnlogin = findViewById(R.id.button_login);
        mBtnreg = findViewById(R.id.button_to_reg);
        mEdtname = findViewById(R.id.editText_name);
        mEdtpasswd = findViewById(R.id.editText_passwd);

        String strfromreg = this.getIntent().getStringExtra("name");
        String strfromsp = null,passwd = null;
        if (AppConfig.Config_AutoSaveUsername) {
            SharedPreferences sp = getSharedPreferences(AppConfig.KEYSTR_APPCFG_FILENAME, Context.MODE_PRIVATE);
            strfromsp = sp.getString(AppConfig.KEYSTR_USERNAME,null);
            passwd = sp.getString(AppConfig.KEYSTR_USERPASSWD,null);
        }


        if(null!=strfromreg && !strfromreg.equals(""))//注册和SP文件保存的用户名二选一，注册页面传递过来的优先
            mEdtname.setText(strfromreg);
        else if(strfromsp!=null){
            mEdtname.setText(strfromsp);
            mEdtpasswd.setText(passwd);
        }

        mBtnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//去注册页按钮被点击
                Intent intent = new Intent("com.example.yundong.SHOW_Register_Activity");
                startActivity(intent);
                finish();
            }
        });
        mBtnlogin.setOnClickListener(new View.OnClickListener() {//登录按钮被点击的监听事件处理
            @Override
            public void onClick(View view) {
                if (AppConfig.Config_AutoSaveUsername) {//在SP文件中保存本次登录用户名和密码
                    SharedPreferences sp = getSharedPreferences(AppConfig.KEYSTR_APPCFG_FILENAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    ed.putString(AppConfig.KEYSTR_USERNAME,mEdtname.getText().toString());
                    ed.putString(AppConfig.KEYSTR_USERPASSWD,mEdtpasswd.getText().toString());
                    ed.commit();
                }


                //登录验证代码待实现
                //...

                //跳转至首页
                Intent intent = new Intent("com.example.yundong.SHOW_Main_Activity");
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected  void onStart(){
        Log.d("YDHL", "onStart: 生命期方法被调用");
        super.onStart();
    }
    @Override
    protected  void onResume(){
        Log.d("YDHL", "onResume: 生命期方法被调用");
        super.onResume();
    }
    @Override
    protected  void onPause(){
        Log.d("YDHL", "onPause: 生命期方法被调用");
        super.onPause();
    }
    @Override
    protected  void onStop(){
        Log.d("YDHL", "onStop: 生命期方法被调用");
        super.onStop();
    }
    @Override
    protected  void onDestroy(){
        Log.d("YDHL", "onDestroy: 生命期方法被调用");
        super.onDestroy();
    }
    @Override
    protected  void onRestart(){
        Log.d("YDHL", "onRestart: 生命期方法被调用");
        super.onRestart();
    }
}



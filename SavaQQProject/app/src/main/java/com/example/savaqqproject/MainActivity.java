package com.example.savaqqproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        //Map<String,String > userInfo = FileSaveQQ.getUserInfo(this);

//        使用SharedPreferences
        Map<String,String > userInfo = SPSaveQQ.getUserInfo(this);
        if (userInfo != null) {
            etNumber.setText(userInfo.get("number"));
            etPassword.setText(userInfo.get("password"));
        }

    }

    //初始化界面
    private void initView() {
        etNumber = findViewById(R.id.et_number);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //当单击登录按钮时，获取QQ号码和密码
        String number = etNumber.getText().toString().trim();
        String password = etPassword.getText().toString();

        //检测账号和密码是否正确
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(MainActivity.this, "请输入账号",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }

        //登录成功
        Toast.makeText(MainActivity.this, "登录成功",Toast.LENGTH_SHORT).show();
        //保存用户信息
//        boolean isSavaSuccess = FileSaveQQ.savaUserInfo(this,number,password);

        //使用SharedPreferences
        boolean isSavaSuccess = SPSaveQQ.savaUserInfo(this,number,password);
        if (isSavaSuccess) {
            Toast.makeText(MainActivity.this, "保存成功",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "保存失败",Toast.LENGTH_SHORT).show();
        }
    }
}

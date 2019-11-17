package com.example.broadcastreceiver;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText emIpnumber;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPower();
        emIpnumber = findViewById(R.id.em_ipnumber);
        sp = getSharedPreferences("config",MODE_PRIVATE);

    }

    public void click(View view) {
        String number = emIpnumber.getText().toString().trim();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("number",number);
        Toast.makeText(this, number, Toast.LENGTH_SHORT).show();
        editor.commit();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    //权限动态申请
    public void requestPower() {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission. READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission. READ_PHONE_STATE)) {
                //这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限
            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission. READ_PHONE_STATE,}, 1);
            }
        }
    }
}

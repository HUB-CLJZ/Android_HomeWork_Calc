package com.example.qquiproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  Button btn;
    private EditText et_number;
    private EditText et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final AlertDialog dialog;
//        dialog = new AlertDialog.Builder(MainActivity.this)
//                .setTitle("登录信息")
//                .setMessage("是否确定退出？")
//                .setIcon(R.mipmap.ic_launcher)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .setNegativeButton("取消", null)
//                .create();

        btn = findViewById(R.id.btn_login);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u_name = String.valueOf(et_number.getText());
                String u_password = String.valueOf(et_password.getText());
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                if (!"".equals(u_name) && !"".equals(u_password)) {
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("u_name", u_name);
                    bundle.putCharSequence("u_password", u_password);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
//                dialog.show();
//              Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            }
        });

        //单选对话框
//        new AlertDialog.Builder(this).setTitle("请选择性别")
//                .setIcon(R.mipmap.ic_launcher)
//                .setSingleChoiceItems(new String[]{"男", "女"}, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .setPositiveButton("确定",null)
//                .show();

        //多选对话框
//        new AlertDialog.Builder(this)
//                .setTitle("请添加兴趣爱好")
//                .setIcon(R.mipmap.ic_launcher)
//                .setMultiChoiceItems(new String[]{"旅游","汽车","美食","宠物"},null,null)
//                .setPositiveButton("确定",null)
//                .show();
//


        //进度条对话框
//        ProgressDialog progressDialog;
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("进度条对话框");
//        progressDialog.setIcon(R.mipmap.ic_launcher);
//        progressDialog.setMessage("正在下载，请稍等");
//        //设置水平进度条
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.show();+

    }
}

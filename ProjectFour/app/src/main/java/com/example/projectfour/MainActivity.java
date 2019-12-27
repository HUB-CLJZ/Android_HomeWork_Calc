package com.example.projectfour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
*   根据输入的数，计算其平方
*   ui线程负责输入，和显示。
*   first线程 负责把 输入的数据传给son2，把son2传回来的结果传给ui线程
* ``two线程，负责计算。
**/
public class MainActivity extends AppCompatActivity {
    private EditText et;
    private TextView tv;
    private static Thread first = null;
    private static Thread two = null;
    private static Handler son_first;
    private Handler son_two;
    private Button btn;
    private Handler ui_handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 3) {
                String data = String.valueOf(msg.arg1) ;
                tv.setText(data);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);
        //点击事件
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //first
                if (first == null ||  !first.isAlive()) {
                    first.start();
                } else {
                    Message msg_ui = Message.obtain();
                    msg_ui.what = 3;
                    msg_ui.arg1 = Integer.parseInt(et.getText().toString());
                    son_first.sendMessage(msg_ui);
                }

                //two
                if (two == null ||  !two.isAlive()) {
                    two.start();
                }
            }
        });


        //第一个子线程
        first = new Thread(){
            @Override
            public void run() {
                //准备
                Looper.prepare();
                son_first = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == 2) {
                            Message msg_ui = Message.obtain();
                            msg_ui.what = 3;
                            msg_ui.arg1 = msg.arg1;
                            ui_handler.sendMessage(msg_ui);
                        }
                        if (msg.what == 3) {
                            Message msg_first = Message.obtain();
                            msg_first.what = 1;
                            msg_first.arg1 = msg.arg1;
                            son_two.sendMessage(msg_first);
                        }
                    }

                };

                Looper.loop();
            }
        };

        //第二个子线程
        two = new Thread(){
            @Override
            public void run() {
                //准备
                Looper.prepare();
                son_two = new Handler(){

                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == 1) {
                            int value = msg.arg1;
                            value = value*value;
                            Message msg_two = Message.obtain();
                            msg_two.what = 2;
                            msg_two.arg1 = value;
                            son_first.sendMessage(msg_two);
                        }
                    }

                };
                Looper.loop();
            }
        };
    }
}

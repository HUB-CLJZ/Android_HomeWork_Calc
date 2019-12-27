package com.example.competition_dislocation1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (preferences == null) {
            preferences = getSharedPreferences("guide", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            boolean isfirst = preferences.getBoolean("isfirst", false);
            if (!isfirst) {
                editor.putBoolean("isfirst", true);
                editor.commit();
                new Handler(new Handler.Callback() {
                    //处理接收到的消息的方法
                    @Override
                    public boolean handleMessage(Message arg0) {
                        //实现页面跳转
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                        return false;
                    }
                }).sendEmptyMessageDelayed(0, 10000); //表示延时三秒进行任务的执行
            }
        }
    }
}
package com.example.music;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText path;
    private Intent intent;
    private myConn conn;
    MusicService.MyBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        path = findViewById(R.id.et_name);
        findViewById(R.id.tv_play).setOnClickListener(this);
        findViewById(R.id.tv_pause).setOnClickListener(this);
        conn = new myConn();
        intent = new Intent(this,MusicService.class);
        intent.setPackage(getPackageName());
        bindService(intent,conn,BIND_AUTO_CREATE);
    }


    private class myConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MusicService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
    @Override
    public void onClick(View v) {
        String pathWay = path.getText().toString().trim();
        switch (v.getId()) {
            case R.id.tv_play:
                if (!TextUtils.isEmpty(pathWay)) {
                    binder.play(pathWay);
                } else {
                    Toast.makeText(this,"找不到音乐文件",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_pause:
                binder.pause();
                break;
            default:
                onDestory();
                break;
        }
    }

    protected void onDestory() {
        unbindService(conn);
        super.onDestroy();
    }

}

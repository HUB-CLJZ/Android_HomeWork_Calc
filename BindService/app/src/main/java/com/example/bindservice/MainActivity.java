package com.example.bindservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyService.Mybinder mybinder;
    private MyConn myConn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnBind(View view) {
        if (myConn == null) {
            myConn = new MyConn();
        }
        Intent intent = new Intent(this, MyService.class);
        bindService(intent,myConn,BIND_AUTO_CREATE);
    }

    public void btnCall(View view) {
        mybinder.callMethodInservice();
    }

    public void btnUnbind(View view) {
        if (myConn != null) {
            unbindService(myConn);
            myConn = null;
        }
    }

    private class MyConn implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder service) {
            mybinder = (MyService.Mybinder)service;
            Log.i("MainActivity","服务绑定成功，内存地址为"+mybinder.toString());
        }
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}

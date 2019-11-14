package com.example.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    class Mybinder extends Binder{
        public void callMethodInservice() {
            methodInService();
        }
    }

    @Override
    public void onCreate() {
        Log.i("MyService","创建服务，调用onCreate()");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("MyService","创建服务，调用onBind()");
        return new Mybinder();
    }

    public void methodInService() {
        Log.i("MyService","自定义方法methodInService()");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("MyService","解绑服务，调用onUnbind()");
        return super.onUnbind(intent);
    }
}

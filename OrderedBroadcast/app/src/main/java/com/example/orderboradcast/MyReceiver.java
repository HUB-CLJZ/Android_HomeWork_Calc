package com.example.orderboradcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyReceiver","自定义有序广播一收到了");
        //拦截广播
        abortBroadcast();
        Log.i("MyReceiver","广播被我拦截了");
    }
}

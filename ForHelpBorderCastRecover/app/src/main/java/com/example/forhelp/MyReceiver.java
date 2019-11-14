package com.example.forhelp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyReceiver","自定义广播接收者，收到了求救广播");
        Log.i("MyReceiver",intent.getAction());
    }
}

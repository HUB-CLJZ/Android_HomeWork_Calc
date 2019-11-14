package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private static final String AC1 = "zzzz";
    private static final String AC2 = "mmmm";
    @Override
    public void onReceive(Context context, Intent intent) {
       if (intent.getAction().equals(AC1)) {
           Toast.makeText(context,"扎克收到",Toast.LENGTH_SHORT).show();
       } else if (intent.getAction().equals(AC2)) {
           Toast.makeText(context,"马云收到",Toast.LENGTH_SHORT).show();
       }
    }
}

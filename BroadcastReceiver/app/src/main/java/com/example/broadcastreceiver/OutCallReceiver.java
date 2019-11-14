package com.example.broadcastreceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String outcalllnumber = getResultData();
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        String number = sp.getString("number","");
        if (outcalllnumber.equals(number)) {
            setResultData(null);
        }
    }
}
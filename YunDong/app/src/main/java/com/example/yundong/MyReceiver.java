package com.example.yundong;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver(){
        Log.d("YDHL","MyReceiver start");
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        if (AppConfig.Sport_NO_CALL) { //免打扰，监听来电
            Log.d("YDHL", intent.getAction());
            AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            //下面代码拿不到电话号码，安卓已作废这种方式
            String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int state = telephony.getCallState();
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.i("YDHL", "等待接电话=修改音量为静默模式");
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.i("YDHL", "电话挂断=恢复音量为之前模式");
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.i("YDHL", "通话中=");
                    break;
            }
        }
    }

}

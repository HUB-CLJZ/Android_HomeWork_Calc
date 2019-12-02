package com.example.handlerminer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class MinerThread  extends  Thread{
    private Handler childHandler;
    private Handler parentHandler = new ParentHandler();
    public static int MinerWhat = 0x005;
    private int maxCont = 100;

    @Override
    public void run() {
        final Message message = new Message();
        message.what = MinerWhat;
        message.arg1 = maxCont;
        parentHandler.sendMessage(message);
        Looper.prepare();
        childHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == MainActivity.parentWhat) {
                    Log.d("CLJZ", "UIHandleMessage: "+msg.obj);
                }
            }
        };
        Looper.loop();
    }

    public Handler getChildHandler() {
        return childHandler;
    }

    public void setChildHandler(Handler childHandler) {
        this.childHandler = childHandler;
    }
}

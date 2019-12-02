package com.example.calc;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
/**
 * @author admin
 */
public class MinerThread extends Thread {
    private int maxValue = 100;
    static int msgMiner = 0x009;
    private android.os.Handler handler;

    static android.os.Handler minerHandler;
    private int i = 1;

    public MinerThread(int maxValue) {
        this.maxValue = maxValue;
    }

    public MinerThread() {

    }
    @Override
    public void run() {
        Log.d("LINJUNFENG", "MinerThread: "+Thread.currentThread().getId());
        if (i <= maxValue) {
            Message message = Message.obtain();
            message.what = msgMiner;
            message.arg1 = i;
            minerHandler.sendMessageDelayed(message,1000);
            i++;
        };
        Log.d("LINJUNFENG", "MinerThread: over");
    }

    public android.os.Handler getHandler() {
        return handler;
    }

    public void setHandler(android.os.Handler handler) {
        this.handler = handler;
    }

    public Handler getMinerHandler() {
        return minerHandler;
    }

    public void setMinerHandler(Handler minerHandler) {
        MinerThread.minerHandler = minerHandler;
    }


}

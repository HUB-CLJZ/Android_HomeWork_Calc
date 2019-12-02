package com.example.handlerminer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
public class ParentHandler extends Handler {
    @Override
    public void handleMessage(@NonNull Message msg) {
        Message msg_ui = Message.obtain();
        msg_ui.what = MainActivity.parentWhat;
        if (msg.what == MainActivity.parentWhat) {
            String str = (String) msg.obj;
            MainActivity.textView.setText(str);
            MainActivity.uiHandler.sendMessage(msg_ui);
        } else if (msg.what == MainActivity.childWhat) {
            String str = (String) msg.obj;
            MainActivity.button.setText(str);
        } else if (msg.what == MinerThread.MinerWhat) {
//          String str = (String) msg.obj;
//          button.setText(str);
            Log.d("CLJZ", "SONhandleMessage: "+msg.arg1);
        }
        super.handleMessage(msg);
    }


}

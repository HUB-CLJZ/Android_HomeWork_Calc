package com.example.appstatechange;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
/**
 * @author admin
 */
public class MyReceiver extends BroadcastReceiver {
    public static String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            //应用卸载
            Toast.makeText(context, "\"应用卸载了，包名为：\"+intent.getData()", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "应用卸载了，包名为："+intent.getData());
        } else if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            //应用安装
            Log.d(TAG, "应用安装了，包名为："+intent.getData());
            Toast.makeText(context, "\"应用安装了，包名为：\"+intent.getData()", Toast.LENGTH_SHORT).show();
        }
    }
}

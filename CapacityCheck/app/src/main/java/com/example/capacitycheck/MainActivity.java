package com.example.capacitycheck;
import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private static String TAG = "MainActivity";
    private BatteryLevelReceiver batteryLevelReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.show);

        //接收的是电量变化
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        //接收usb断开,插上
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);


        BatteryLevelReceiver batteryLevelReceiver = new BatteryLevelReceiver();
        //注册广播
        this.registerReceiver(batteryLevelReceiver,intentFilter);
    }


    /**
      * @Package:        com.example.capacitycheck
      * @ClassName:      MainActivity
      * @Description:    创建一个广播接收者
      * @Author:         CLJZ
      * @CreateDate:     2019/11/30 14:49
      * @UpdateUser:     CLJZ
      * @UpdateDate:     2019/11/30 14:49
      * @UpdateRemark:   更新说明：
      * @Version:        1.0
     */
    private class BatteryLevelReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int value = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            String textValue =  "电量"+value + "\nusb状态："+"";
            //Log.d(TAG, "收到了电量变化的广播: "+action);
            //获取电量的值
            if (textView != null) {
                textView.setText(textValue);
            }
            if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
                Log.d(TAG, "usb已连接: ");
                textView.setText(textValue+"usb已连接");
            } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
                Log.d(TAG, "usb已断开: ");
                textView.setText(textValue+"usb已连接");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消广播注册
        if (batteryLevelReceiver != null) {
            this.unregisterReceiver(batteryLevelReceiver);
        }
    }
}
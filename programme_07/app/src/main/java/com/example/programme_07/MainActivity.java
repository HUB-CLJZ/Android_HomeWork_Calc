package com.example.programme_07;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
    private TextView tvOpen;
    private Switch swSwitch;
    private SeekBar pbTemperature;
    private SeekBar pbHumidity;
    private SeekBar pbLight;
    private boolean isStart = true;
    private final Handler handler = new Handler();
    private Button btn;

    //设置通知的ID
    private static final int TEMPERATURE = 0x005;
    private static final int HUMIDITY = 0x006;
    private static final int LIGHT = 0x007;

    //规定的阀值
    private static int maxValueTemperature = 95;
    private static int maxValueHumidity = 95;
    private static int maxValueLight = 95;

    //通知，用于处理阀值
    private NotificationManager mNotificationManager = null;
    private Notification notification = null;

    //监听的时间间隔
    private static int time = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //设置监听的初始值
        if (swSwitch.isChecked()) {
            //不可以保存
            btn.setEnabled(false);
            pbHumidity.setEnabled(false);
            pbLight.setEnabled(false);
            pbTemperature.setEnabled(false);
            handler.postDelayed(task, time);
        }
        //点击事件的处理
        onClickMethod();
        //添加阀值预警
        threshold_warning_method();
    }


    private void threshold_warning_method() {
        //对湿度的改变值的监听
        pbHumidity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > maxValueHumidity) {
                    sendNotice("湿度超过阀值预警", "阈值" + maxValueHumidity + "当前值" + progress, HUMIDITY);
                } else if (mNotificationManager != null && progress <= maxValueHumidity) {
                    mNotificationManager.cancel(HUMIDITY);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        //对光照的改变值的监听
        pbLight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > maxValueLight) {
                    sendNotice("光照超过阀值预警", "阈值" + maxValueLight + "当前值" + progress, LIGHT);
                } else if (mNotificationManager != null && progress <= maxValueLight) {
                    mNotificationManager.cancel(LIGHT);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //对温度的改变值的监听
        pbTemperature.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > maxValueTemperature) {
                    sendNotice("温度超过阀值预警", "阈值" + maxValueTemperature + "当前值" + progress, TEMPERATURE);
                } else if (mNotificationManager != null && progress <= maxValueTemperature) {
                    mNotificationManager.cancel(TEMPERATURE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //按钮和switch组件按下的监听
    private void onClickMethod() {
        swSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //不可以保存
                    btn.setEnabled(false);
                    tvOpen.setText("开");
                    pbHumidity.setEnabled(false);
                    pbLight.setEnabled(false);
                    pbTemperature.setEnabled(false);
                    isStart = true;
                    handler.postDelayed(task, time);
                } else {
                    tvOpen.setText("关");
                    //可以保存
                    btn.setEnabled(true);
                    pbHumidity.setEnabled(true);
                    pbLight.setEnabled(true);
                    pbTemperature.setEnabled(true);
                    isStart = false;

                    //设置进度值为阀值
                    pbHumidity.setProgress(maxValueHumidity);
                    pbTemperature.setProgress(maxValueTemperature);
                    pbLight.setProgress(maxValueLight);
                }
            }
        });

        //改变设置的阀值
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLJZ", "温度: " + pbTemperature.getProgress());
                maxValueTemperature = pbTemperature.getProgress();

                Log.d("CLJZ", "湿度: " + pbHumidity.getProgress());
                maxValueHumidity = pbHumidity.getProgress();

                Log.d("CLJZ", "光照: " + pbLight.getProgress());
                maxValueLight = pbLight.getProgress();

                Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化控件
    private void initView() {
        tvOpen = findViewById(R.id.tv_open);
        swSwitch = findViewById(R.id.sw_switch);
        pbTemperature = findViewById(R.id.pb_temperature);
        pbHumidity = findViewById(R.id.pb_humidity);
        pbLight = findViewById(R.id.pb_light);
        btn = findViewById(R.id.btn_sava);
    }

    //随机产生参数的方法
    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (isStart) {
               /* pbTemperature.postInvalidate();
                pbLight.postInvalidate();
                pbHumidity.postInvalidate();*/
                pbTemperature.setProgress(new Random().nextInt(100 - 0 + 1) - 1);
                pbLight.setProgress(new Random().nextInt(100 - 0 + 1) - 1);
                pbHumidity.setProgress(new Random().nextInt(100 - 0 + 1) - 1);
                handler.postDelayed(this, time);
            }
        }
    };

    //发送通知的方法
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotice(String title, String content, int id) {
        if (isStart) {
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //安卓8.0以后的实现方法，安卓26以后才能使用
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                //通知的链接的内容是一个网址
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jianshu.com/p/890acf8e5080"));
                //通知满足条件后执行
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);

                NotificationChannel channel = new NotificationChannel("default", "default"
                        , NotificationManager.IMPORTANCE_DEFAULT);
                mNotificationManager.createNotificationChannel(channel);
                notification = new Notification.Builder(MainActivity.this, "default")
                        .setContentTitle(title)
                        .setContentText(content)
                        .setContentIntent(pendingIntent)
                        .setFullScreenIntent(pendingIntent, true)
                        .setSmallIcon(R.drawable.menu) //通知栏没展开之前的那个小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//通知栏展开后的那个大图标
                        .build();
            } else {
                notification = new Notification.Builder(MainActivity.this)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setSmallIcon(R.drawable.menu) //通知栏没展开之前的那个小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//通知栏展开后的那个大图标
                        .build();
            }
            //发送通知
            mNotificationManager.notify(id, notification);
        }
    }
}
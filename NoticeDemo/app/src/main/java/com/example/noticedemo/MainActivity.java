package com.example.noticedemo;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {

    private Button btn_open;
    private Button btn_close;
    private NotificationManager mNotificationManager;
    private Notification notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //打开通知
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*  //当前效果，安卓8.0以后无效
                mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new Notification.Builder(MainActivity.this)
                        .setContentTitle("我是标题")
                        .setContentText("我是通知内容")
                        .setSmallIcon(R.drawable.ic_launcher_background) //通知栏没展开之前的那个小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))//通知栏展开后的那个大图标
                        .build();
                //发送通知
                mNotificationManager.notify(10,notification);*/

                mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //安卓8.0以后的实现方法，安卓26以后才能使用
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("default","default"
                            ,NotificationManager.IMPORTANCE_DEFAULT);
                    mNotificationManager.createNotificationChannel(channel);
                    notification= new Notification.Builder(MainActivity.this,"default")
                            .setContentTitle("我是标题")
                            .setContentText("我是通知内容")
                            .setSmallIcon(R.drawable.ic_launcher_background) //通知栏没展开之前的那个小图标
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))//通知栏展开后的那个大图标
                            .build();
                } else {
                    notification = new Notification.Builder(MainActivity.this)
                            .setContentTitle("我是标题")
                            .setContentText("我是通知内容")
                            .setSmallIcon(R.drawable.ic_launcher_background) //通知栏没展开之前的那个小图标
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))//通知栏展开后的那个大图标
                            .build();
                }

                //发送通知
                mNotificationManager.notify(10,notification);


            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotificationManager.cancel(10);
            }
        });

    }

    private void initView() {
        btn_open = findViewById(R.id.btn_open);
        btn_close = findViewById(R.id.btn_close);
    }


}

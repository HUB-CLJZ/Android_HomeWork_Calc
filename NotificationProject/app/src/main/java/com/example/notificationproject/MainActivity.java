package com.example.notificationproject;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import static com.example.notificationproject.R.*;
public class MainActivity extends AppCompatActivity {
    NotificationManager mNotificationManager;
    /**
     * @description:对方法的描述
     * @author: CLJZ
     * @date: 2019/11/10  18:05
     * @param:
     * @return:
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        //基础的通知
        baseNotification();
/*----------------------------------------------------------------------------------------------------------*/
        //基础的通知添加扩展通知自定义布局
        //addLayoutNotification();
    }

    /**
     * @description:演示基本通知的方法
     * @author: CLJZ
     * @date: 2019/11/10  16:18
     * @param: []
     * @return: void
     */
    public void baseNotification() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //初始化通知管理器
        //构造一个通知
        Notification.Builder builder = new Notification.Builder(this);
        //通知的链接的内容是一个网址
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jianshu.com/p/890acf8e5080"));
        //通知满足条件后执行
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,mIntent,0);
        //设置通知为pedingIntent
        builder.setContentIntent(pendingIntent);
        //设置通知的图标
        builder.setSmallIcon(drawable.ic_launcher_background);

        builder.setFullScreenIntent(pendingIntent,true);
        //设置通知的标题
        builder.setContentTitle("标题");

        //通知的取消按钮
        builder.setAutoCancel(true);
        //通知的内容
        builder.setContentText("通知消息");
        //将构造的通知加入到通知中
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }
        //添加该通知到通知管理器中
        mNotificationManager.notify(1, notification);
    }


    /**
     * @description:基础通知添加自定义布局的方法
     * @author: CLJZ
     * @date: 2019/11/10  16:37
     * @param: []
     * @return: void
     */
    private void addLayoutNotification() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder2 = new Notification.Builder(this);
        Intent intent2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jianshu.com/p/82e249713f1b"));
        PendingIntent pendingIntent2=PendingIntent.getActivity(this,0,intent2,0);
        builder2.setContentIntent(pendingIntent2);
        builder2.setSmallIcon(mipmap.ic_launcher);
        builder2.setLargeIcon(BitmapFactory.decodeResource(getResources(), mipmap.ic_launcher));
        builder2.setAutoCancel(true);
        builder2.setContentTitle("折叠通知");
        RemoteViews remoteViews=new RemoteViews(getPackageName(), layout.item_notification);
        Notification  notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder2.build();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification.bigContentView = remoteViews;
        }
        mNotificationManager.notify(2,notification);
    }

}

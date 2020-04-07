package com.example.programme_29;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LineChart lc;

    private int[] colors = new int[]{Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lc = findViewById(R.id.lineChart);

        List<Entry> data = new ArrayList<>();

        //刻度的设置
        List<Entry> lineCalibration = new ArrayList<>();

        List<Entry> lineCalibrationY = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            Random random = new Random();
            int number = random.nextInt(300 - 10 + 1) + 10;
            data.add(new Entry(i, number));
        }

        //x轴添加刻度
        for (int i = 1; i < 6; i++) {
            lineCalibration.add(new Entry(i, 20, getResources().getDrawable(R.drawable.layer_list)));
        }

        //y轴添加刻度
        for (int i = 40; i <= 320; i += 20) {
            lineCalibrationY.add(new Entry(0, i, getResources().getDrawable(R.drawable.layer_list_y)));
        }


        //分别通过每一组Entry对象集合的数据创建折线数据集
        LineDataSet lineDataSet1 = new LineDataSet(data, "PM2.5指数");
        LineDataSet setXLine = new LineDataSet(lineCalibration, "刻度");
        LineDataSet setYLine = new LineDataSet(lineCalibrationY, "Y轴刻度");

        //设置刻度的样式
        setXLine.setColor(Color.BLACK);
        setXLine.setDrawIcons(true);
        setXLine.setDrawValues(false);
        setXLine.setDrawCircles(false);

        setYLine.setColor(Color.BLACK);
        setYLine.setDrawIcons(true);
        setYLine.setDrawValues(false);
        setYLine.setDrawCircles(false);


        //折现的颜色
        lineDataSet1.setColor(Color.BLACK);

        //设置显示模式
        lineDataSet1.setMode(LineDataSet.Mode.LINEAR);

        lineDataSet1.setDrawValues(true);

        //折现点的颜色
        lineDataSet1.setCircleColors(colors);

        //设置折点实心显示
        lineDataSet1.setDrawCircleHole(false);

        // 将每一组折线数据集添加到折线数据中
        LineData lineData = new LineData(lineDataSet1, setXLine, setYLine);

        //获取此图表的x轴
        XAxis xAxis = lc.getXAxis();

        xAxis.setAxisLineColor(Color.BLACK);

        //设置x轴线宽度和颜色
        xAxis.setAxisLineWidth(1F);
        xAxis.setAxisLineColor(Color.BLACK);


        //不显示格子的竖线
        xAxis.setDrawGridLines(false);

        //重新设置X轴的标注值
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add((3 * i) + "");
        }

        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));

        xAxis.setTextSize(12F);

        //设置x轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //X轴显示每个月份
        xAxis.setLabelCount(6, true);

        //Y轴右半部分不显示
        YAxis rightYAris = lc.getAxisRight();
        rightYAris.setEnabled(false);

        YAxis leftYAris = lc.getAxisLeft();

        leftYAris.setTextSize(12F);

        leftYAris.setDrawGridLines(true);

        //y轴的颜色
        leftYAris.setAxisLineWidth(1F);
        leftYAris.setAxisLineColor(Color.BLACK);

        //横坐标的颜色为透明
        leftYAris.setGridColor(Color.TRANSPARENT);


        //设置Y轴最低值，最高值，和每个值之间的间距
        leftYAris.setAxisMinimum(20F);
        leftYAris.setAxisMaximum(380F);
        leftYAris.setLabelCount(1, false);

        //取消拖动高亮线
        lc.setHighlightPerDragEnabled(false);

        //左下角图例
        Legend legend = lc.getLegend();
        legend.setEnabled(false);

        //去除右下方描述
        Description description = new Description();
        description.setEnabled(false);
        lc.setDescription(description);

        lc.setData(lineData);
    }

    /***
     * 通知的使用
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void getNotice() {
        final NotificationManager[] mNotificationManager = new NotificationManager[1];
        final Notification[] notification = new Notification[1];

        /*
                //当前效果，安卓8.0以后无效
               mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
               Notification notification = new Notification.Builder(MainActivity.this)
                        .setContentTitle("我是标题")
                        .setContentText("我是通知内容")
                        .setSmallIcon(R.drawable.ic_launcher_background) //通知栏没展开之前的那个小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))//通知栏展开后的那个大图标
                        .build();
                //发送通知
                 mNotificationManager.notify(10,notification);
       */

        mNotificationManager[0] = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //安卓8.0以后的实现方法，安卓26以后才能使用
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "default"
                    , NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager[0].createNotificationChannel(channel);
            notification[0] = new Notification.Builder(MainActivity.this, "default")
                    .setContentTitle("我是标题")
                    .setContentText("我是通知内容")
                    .setSmallIcon(R.drawable.ic_launcher_background) //通知栏没展开之前的那个小图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//通知栏展开后的那个大图标
                    .build();
        } else {
            notification[0] = new Notification.Builder(MainActivity.this)
                    .setContentTitle("我是标题")
                    .setContentText("我是通知内容")
                    .setSmallIcon(R.drawable.ic_launcher_background) //通知栏没展开之前的那个小图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//通知栏展开后的那个大图标
                    .build();
        }
        //发送通知
        mNotificationManager[0].notify(10, notification[0]);
    }
}

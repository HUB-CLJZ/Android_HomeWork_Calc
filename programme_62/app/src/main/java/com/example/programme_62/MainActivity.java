package com.example.programme_62;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadarChart mRadarChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //设置雷达图数据
        setData();

    }

    private void initView() {
        mRadarChart = findViewById(R.id.radar_chart);
    }

    private void initChart() {

        //设置web线的颜色(即就是外面包着的那个颜色)
        mRadarChart.setWebColorInner(Color.parseColor("#87939a"));

        //设置中心线颜色(也就是竖着的线条)
        mRadarChart.setWebColor(Color.parseColor("#87939a"));
        mRadarChart.setWebAlpha(100);

        XAxis xAxis = mRadarChart.getXAxis();

        //设置x轴标签字体颜色
        xAxis.setDrawLabels(false);

        YAxis yAxis = mRadarChart.getYAxis();

        //设置y轴的标签个数
        yAxis.setLabelCount(5, true);

        //设置y轴从0f开始
        yAxis.setAxisMinimum(0F);
        yAxis.setAxisMaximum(100F);

        yAxis.setDrawTopYLabelEntry(true);

        //设置字体大小
        yAxis.setTextSize(15f);

        //设置字体颜色
        yAxis.setTextColor(Color.parseColor("#2b2b2b"));

        yAxis.setDrawTopYLabelEntry(true);


        //启用线条，如果禁用，则无任何线条
        mRadarChart.setDrawWeb(true);

        //设置图例位置

        Legend legend = mRadarChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);

        //禁用图表描述
        mRadarChart.getDescription().setEnabled(false);
    }

    private void setData() {

        List<RadarEntry> data1 = new ArrayList<>();
        List<RadarEntry> data2 = new ArrayList<>();
        List<RadarEntry> data3 = new ArrayList<>();
        List<RadarEntry> data4 = new ArrayList<>();
        List<RadarEntry> data5 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            data1.add(new RadarEntry((float) (Math.random() * 100)));
            data2.add(new RadarEntry((float) (Math.random() * 100)));
            data3.add(new RadarEntry((float) (Math.random() * 100)));
            data4.add(new RadarEntry((float) (Math.random() * 100)));
            data5.add(new RadarEntry((float) (Math.random() * 100)));
        }

        RadarDataSet radarDataSet1 = new RadarDataSet(data1, "驾驶摩托车在车把上悬挂物品的");
        RadarDataSet radarDataSet2 = new RadarDataSet(data2, "拖拉机驶入大中城市中心城区内道路的");
        RadarDataSet radarDataSet3 = new RadarDataSet(data3, "拖拉机违反规定载人的");
        RadarDataSet radarDataSet4 = new RadarDataSet(data4, "拖拉机牵引多辆挂车的");
        RadarDataSet radarDataSet5 = new RadarDataSet(data5, "学习驾驶人不按指定时间上道路学习驾驶的");

        //禁用标签
        radarDataSet1.setDrawValues(false);
        radarDataSet2.setDrawValues(false);
        radarDataSet3.setDrawValues(false);
        radarDataSet4.setDrawValues(false);
        radarDataSet5.setDrawValues(false);

        //设置填充颜色
        radarDataSet1.setFillColor(Color.parseColor("#36a9ce"));
        radarDataSet2.setFillColor(Color.parseColor("#33ff66"));
        radarDataSet3.setFillColor(Color.parseColor("#ef5aa1"));
        radarDataSet4.setFillColor(Color.parseColor("#ff0000"));
        radarDataSet5.setFillColor(Color.parseColor("#6600ff"));

        radarDataSet1.setColor(Color.parseColor("#36a9ce"));
        radarDataSet2.setColor(Color.parseColor("#33ff66"));
        radarDataSet3.setColor(Color.parseColor("#ef5aa1"));
        radarDataSet4.setColor(Color.parseColor("#ff0000"));
        radarDataSet5.setColor(Color.parseColor("#6600ff"));

        //设置启用填充
        radarDataSet1.setDrawFilled(true);
        radarDataSet2.setDrawFilled(true);
        radarDataSet3.setDrawFilled(true);
        radarDataSet4.setDrawFilled(true);
        radarDataSet5.setDrawFilled(true);

        //初始化雷达图
        initChart();

        //添加两条数据
        RadarData data = new RadarData(drawAngleCircle(),radarDataSet1,radarDataSet2,radarDataSet3,radarDataSet4,radarDataSet5);
        mRadarChart.setData(data);
        mRadarChart.invalidate();
    }

    /**
     * 创建一组虚拟图表数据
     * @return
     */
    private RadarDataSet drawAngleCircle() {
        List<RadarEntry> yVals = new ArrayList<>();
        // 各个顶点的图标资源
        int[] drawables = new int[]{
                R.drawable.shape_circle_1,
                R.drawable.shape_circle_2,
                R.drawable.shape_circle_3,
                R.drawable.shape_circle_4,
                R.drawable.shape_circle_5,
                R.drawable.shape_circle_3
        };
        for (int i = 0; i < 5; i++) {
            RadarEntry radarEntry = new RadarEntry(100F);
            //为每个数据设置一个图标
            radarEntry.setIcon(getResources().getDrawable(drawables[i]));
            yVals.add(radarEntry);
        }
        RadarDataSet ds = new RadarDataSet(yVals, "");
        // 不显示数据连线
        ds.setColors(Color.TRANSPARENT);
        // 不绘制数据值
        ds.setDrawValues(false);

        return ds;
    }
}

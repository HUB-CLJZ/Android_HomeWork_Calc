package com.example.programme_50;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * 未解决问题：图表的刻度
 */
public class MainActivity extends AppCompatActivity {

    private BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //  柱形图的数据
        List<BarEntry> values1 = new ArrayList<>();
        List<BarEntry> values2 = new ArrayList<>();
        List<BarEntry> values3 = new ArrayList<>();
        List<BarEntry> values4 = new ArrayList<>();
        List<BarEntry> values5 = new ArrayList<>();
        List<BarEntry> values6 = new ArrayList<>();
        List<BarEntry> values7 = new ArrayList<>();


        for (Integer i = 0; i < 7; i++) {
            //  添加数据
            values1.add(new BarEntry(i, 2*(new Random().nextInt(4 - 1 + 1) + 1+0.5F)));
            values2.add(new BarEntry(i, 2*(new Random().nextInt(4 - 1 + 1) + 1+0.5F)));
            values3.add(new BarEntry(i, 2*(new Random().nextInt(4 - 1 + 1) + 1+0.5F)));
            values4.add(new BarEntry(i, 2*(new Random().nextInt(4 - 1 + 1) + 1+0.5F)));
            values5.add(new BarEntry(i, 2*(new Random().nextInt(4 - 1 + 1) + 1+0.5F)));
            values6.add(new BarEntry(i, 2*(new Random().nextInt(4 - 1 + 1) + 1+0.5F)));
            values7.add(new BarEntry(i, 2*(new Random().nextInt(4 - 1 + 1) + 1+0.5F)));

        }

        // 添加进图标，设置数据的含义
        BarDataSet dataSet1 = new BarDataSet(values1, "学院路 ");
        BarDataSet dataSet2 = new BarDataSet(values2, "联想路");
        BarDataSet dataSet3 = new BarDataSet(values3, "医院路");
        BarDataSet dataSet4 = new BarDataSet(values4, "幸福路");
        BarDataSet dataSet5 = new BarDataSet(values5, "环城快速路");
        BarDataSet dataSet6 = new BarDataSet(values6, "环城高速");
        BarDataSet dataSet7 = new BarDataSet(values7, "停车场");


        //设置柱形的颜色
        dataSet1.setColor(Color.parseColor("#c23531"));
        dataSet2.setColor(Color.parseColor("#2f4554"));
        dataSet3.setColor(Color.parseColor("#61a0a8"));
        dataSet4.setColor(Color.parseColor("#d48265"));
        dataSet5.setColor(Color.parseColor("#91c7ae"));
        dataSet6.setColor(Color.parseColor("#749f83"));
        dataSet7.setColor(Color.parseColor("#ca8622"));

        dataSet1.setDrawValues(false);
        dataSet2.setDrawValues(false);
        dataSet3.setDrawValues(false);
        dataSet4.setDrawValues(false);
        dataSet5.setDrawValues(false);
        dataSet6.setDrawValues(false);
        dataSet7.setDrawValues(false);

        //初始化数据
        BarData barData = new BarData(dataSet1, dataSet2, dataSet3,dataSet4,dataSet5,dataSet6,dataSet7);

        //柱状图组之间的间距
        float groupSpace = 0.5F;
        //每个组柱形的直接的间距
        float barSpace = 0f;
        //设置柱状图宽度
        barData.setBarWidth(0.0714285714285714F);
        //(起始点、柱状图组间距、柱状图之间间距)
        barData.groupBars(-0.5F, groupSpace, barSpace);

        //图例显示位置
        Legend legend = mBarChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextSize(10);

        //  去除右下方描述
        Description description = new Description();
        description.setEnabled(false);
        mBarChart.setDescription(description);

        //X轴的位置显示在下方
        XAxis xAxis = mBarChart.getXAxis();

        //重新设置X轴的标注值
        String month[] = {"周一","周二","周三","周四","周五","周六","周日"};
        List<String> list = new ArrayList<>();
        for (int i = 0; i < month.length; i++) {
            list.add(month[i]);
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));
        xAxis.setLabelCount(7,false);

        //设置x轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //设置y轴
        YAxis yAxisRight =  mBarChart.getAxisRight();
        List<String> yRightList = new ArrayList<>();
        for (int i  = 0; i < 10; i++) {
            yRightList.add(String.valueOf(i));
        }
        yAxisRight.setValueFormatter(new IndexAxisValueFormatter(yRightList));
        yAxisRight.setAxisMinimum(0F);
        yAxisRight.setAxisMaximum(5F);
        yAxisRight.setLabelCount(6,true);

        YAxis yAxisLeft = mBarChart.getAxisLeft();
        String yLeft[] =  {"","畅通","","缓行","","一般拥堵","","中度拥堵","","严重拥堵"};
        List<String> yLeftList = new ArrayList<>();
        for (int i = 0; i < yLeft.length; i++) {
            yLeftList.add(yLeft[i]);
        }
        yAxisLeft.setValueFormatter(new IndexAxisValueFormatter(yLeftList));
        yAxisLeft.setAxisMinimum(0F);
        yAxisLeft.setAxisMaximum(10F);
        yAxisLeft.setLabelCount(11,true);
        yAxisLeft.setDrawGridLines(false);

        // 设置是否可以触摸
        mBarChart.setTouchEnabled(false);

        // 是否可以拖拽
        mBarChart.setDragEnabled(false);

        // 是否可以缩放
        mBarChart.setScaleEnabled(false);

        //y轴的值是否跟随图表变换缩放;如果禁止，y轴的值会跟随图表变换缩放
        mBarChart.setPinchZoom(false);

        //柱状图没有数据的部分是否显示阴影效果
        mBarChart.setDrawBarShadow(false);

        //柱状图上面的数值是否在柱子上面
        mBarChart.setDrawValueAboveBar(true);

        //是否显示竖直标尺线
        mBarChart.getXAxis().setDrawGridLines(false);

        //添加边框
        mBarChart.setDrawBorders(true);

        //添加数据
        mBarChart.setData(barData);

    }

    private void initView() {
        mBarChart = (BarChart) findViewById(R.id.bar_chart);
    }
}
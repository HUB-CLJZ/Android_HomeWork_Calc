package com.example.barchardemo;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
/***
 * 参考文献： https://www.cnblogs.com/xuqp/p/11699754.html
 *          https://blog.csdn.net/github_33304260/article/details/51272078
 *
 *
 * 水平柱状图：https://blog.csdn.net/weixin_43344890/article/details/103008320
 *
 * */
public class MainActivity extends AppCompatActivity {
    private BarChart mBarChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBarChart = findViewById(R.id.bar_chart);

        //  柱形图的数据
        List<BarEntry> values1 = new ArrayList<>();
        List<BarEntry> values2 = new ArrayList<>();
        List<BarEntry> values3 = new ArrayList<>();

        for (Integer i = 0; i < 6; i++) {
            //  添加数据
            values1.add(new BarEntry(i,10*i+05));
            values2.add(new BarEntry(i,10*i+10));
            values3.add(new BarEntry(i,10*i+15));
        }

        // 添加进图标，设置数据的含义
        BarDataSet dataSet1 = new BarDataSet(values1, "Label");
        BarDataSet dataSet2 = new BarDataSet(values2, "Labe2");
        BarDataSet dataSet3 = new BarDataSet(values3, "Labe3");

        //  设置柱形的颜色
        dataSet1.setColor(Color.RED);
        dataSet2.setColor(Color.GREEN);
        dataSet3.setColor(Color.BLUE);

        //  设置值的颜色
        dataSet1.setValueTextColor(Color.RED);
        dataSet2.setValueTextColor(Color.RED);
        dataSet3.setValueTextColor(Color.RED);

        //初始化数据
        BarData barData = new BarData(dataSet1,dataSet2,dataSet3);

        /**
         * float groupSpace = 0.3f;   //柱状图组之间的间距
         * float barSpace =  0.05f;  //每条柱状图之间的间距  一组两个柱状图
         * float barWidth = 0.3f;    //每条柱状图的宽度     一组两个柱状图
         * (barWidth + barSpace) * barAmount + groupSpace = (x+0)*6+y = (1-y)/6=1
         * 3个数值 加起来 必须等于 1 即100% 按照百分比来计算 组间距 柱状图间距 柱状图宽度
         */
        //需要显示柱状图的类别 数量
        int barAmount = values1.size();

        //设置组间距占比30% 每条柱状图宽度占比 70% /barAmount  柱状图间距占比 0%
        //柱状图组之间的间距
        float groupSpace = 0.5F;
        float barWidth = (1f - groupSpace) / barAmount;
        float barSpace = 0f;
        //设置柱状图宽度
        barData.setBarWidth(0.166666666666667F);
        //(起始点、柱状图组间距、柱状图之间间距)
        barData.groupBars(-0.5f, groupSpace, barSpace);



        // 是否显示表格颜色(数据以外的背景色)
        /*mBarChart.setDrawGridBackground(true);

        // 表格的的颜色
        mBarChart.setGridBackgroundColor(Color.WHITE);*/

        // 设置是否可以触摸
        mBarChart.setTouchEnabled(false);

        // 是否可以拖拽
        mBarChart.setDragEnabled(false);

        // 是否可以缩放
        mBarChart.setScaleEnabled(false);

        //  y轴的值是否跟随图表变换缩放;如果禁止，y轴的值会跟随图表变换缩放
        mBarChart.setPinchZoom(false);


        //  柱状图没有数据的部分是否显示阴影效果
        mBarChart.setDrawBarShadow(false);

        //  柱状图上面的数值是否在柱子上面
        mBarChart.setDrawValueAboveBar(true);

        //  是否显示竖直标尺线
        mBarChart.getXAxis().setDrawGridLines(false);

        //  右侧是否显示Y轴数值
        mBarChart.getAxisRight().setDrawLabels(false);
        mBarChart.getAxisLeft().setDrawLabels(true);

        //  是否显示最右侧竖线
        mBarChart.getAxisRight().setEnabled(false);
        mBarChart.getAxisLeft().setEnabled(true);

        //  不设置图例
        Legend legend = mBarChart.getLegend();
        legend.setEnabled(false);

        //  去除右下方描述
        Description description = new Description();
        description.setEnabled(false);
        mBarChart.setDescription(description);

        //  添加边框
        mBarChart.setDrawBorders(false);


        //  X轴的位置显示在下方
        XAxis xAxis = mBarChart.getXAxis();

        //重新设置X轴的标注值
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(String.valueOf(i+1).concat("月"));
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));

        //不显示x轴轴线
        xAxis.setDrawAxisLine(false);


        //设置x轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        //添加数据
        mBarChart.setData(barData);

    }
}
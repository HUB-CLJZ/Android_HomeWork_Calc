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
        List<BarEntry> entries = new ArrayList<>();
        for (Integer i = 1; i <= 5; i++) {

            float valueY = i;
            float valueX = i*10;

            //  添加数据
            entries.add(new BarEntry(valueY, valueX));
        }

        // 添加进图标，设置数据的含义
        BarDataSet dataSet = new BarDataSet(entries, "Label");

        //  设置柱形的颜色
        dataSet.setColor(Color.BLUE);

        //  设置值的颜色
        dataSet.setValueTextColor(Color.RED);

        //  初始化数据
        BarData barData = new BarData(dataSet);

        /*// 是否显示表格颜色(数据以外的背景色)
        mBarChart.setDrawGridBackground(true);

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

        //  设置横坐标显示的间隔数
        mBarChart.getXAxis().setLabelCount(5);

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
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //  添加数据
        mBarChart.setData(barData);
    }
}
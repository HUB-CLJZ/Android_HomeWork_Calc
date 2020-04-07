package com.example.programme_15;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
public class StackActivity extends AppCompatActivity {
    private BarChart mBarChart;
    private List<String> lables = new ArrayList<>();

    private Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);
        mBarChart = findViewById(R.id.bar_chart);

        List<BarEntry> list = new ArrayList<>();
        List<BarEntry> list2 = new ArrayList<>();

        toolbar = findViewById(R.id.tool_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int[] valus1 = {480, 440, 560, 640, 400};
        int[] valus2 = {240, 180, 300, 420, 270};
        int sum = 0;
        for (int i = 0; i < valus1.length; i++) {
            list.add(new BarEntry(i, valus1[i]));
            list2.add(new BarEntry(i,valus2[i]));
            sum += valus1[i];
        }

        // 添加进图标，设置数据的含义
        BarDataSet dataSet1 = new BarDataSet(list, "有违章");
        BarDataSet dataSet2 = new BarDataSet(list2, "无违章");

        //  设置柱形的颜色
        dataSet1.setColor(Color.parseColor("#eb7208"));
        dataSet2.setColor(Color.parseColor("#6a9800"));

        //不显示值
        dataSet2.setDrawValues(false);

        //初始化数据
        BarData barData = new BarData(dataSet1, dataSet2);

        //计算百分比
        for (int i = 0; i < valus1.length; i++) {
            BigDecimal number = new BigDecimal(valus1[i]*100);
            BigDecimal dividend = new BigDecimal(sum);
            BigDecimal result = number.divide(dividend,1, RoundingMode.HALF_UP);
            Log.d("CLJZ", "result: "+result.toString());
            lables.add(result.toString()+"%");
        }
        barData.setBarWidth(0.3F);

        //显示计算的百分比
        dataSet1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return lables.get((int) value % lables.size());
            }
        });

        // 设置是否可以触摸
        mBarChart.setTouchEnabled(false);

        // 是否可以拖拽
        mBarChart.setDragEnabled(false);

        // 是否可以缩放
        mBarChart.setScaleEnabled(false);

        //y轴的值是否跟随图表变换缩放;如果禁止，y轴的值会跟随图表变换缩放
        mBarChart.setPinchZoom(false);


        //  柱状图没有数据的部分是否显示阴影效果
        mBarChart.setDrawBarShadow(false);

        //  柱状图上面的数值是否在柱子上面
        mBarChart.setDrawValueAboveBar(true);


        // 是否显示竖直标尺线
        mBarChart.getXAxis().setDrawGridLines(false);

        //  右侧是否显示Y轴数值
        mBarChart.getAxisLeft().setDrawLabels(true);

        YAxis leftYAris = mBarChart.getAxisLeft();

        leftYAris.setDrawAxisLine(true);

        //设置Y轴最低值，最高值，和每个值之间的间距
        leftYAris.setAxisMinimum(0F);
        leftYAris.setAxisMaximum(1200F);
        leftYAris.setLabelCount(7, true);



        //  是否显示最右侧竖线
        mBarChart.getAxisRight().setEnabled(false);
        mBarChart.getAxisLeft().setEnabled(true);

        //不设置图例
        Legend legend = mBarChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        //去除右下方描述
        Description description = new Description();
        description.setEnabled(false);
        mBarChart.setDescription(description);

        //添加边框
        mBarChart.setDrawBorders(false);

        //X轴的位置显示在下方
        XAxis xAxis = mBarChart.getXAxis();

        //重新设置X轴的标注值
        List<String> years = new ArrayList<>();
        String str_years[] = {"90后", "80后", "70后", "60后", "50后"};
        for (int i = 0; i < str_years.length; i++) {
            years.add(str_years[i]);
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(years));

        xAxis.setLabelCount(5, false);


        //设置x轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //添加数据
        mBarChart.setData(barData);
    }

}

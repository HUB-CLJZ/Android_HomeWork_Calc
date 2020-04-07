package com.example.programme_15;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

/***
 * 滑动样式参考第六题
 *
 */
public class MainActivity extends AppCompatActivity {

    private PieChart mPieChart;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        final int datas[] = {45,55};
        final String[] mess = {"有违章","无违章"};

        //大小
        ArrayList<PieEntry> sizes = new ArrayList<>();
        sizes.add(new PieEntry(datas[0], "有违章"));
        sizes.add(new PieEntry(datas[1], "无违章"));

        //  颜色
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#aa4643"));
        colors.add(Color.parseColor("#4572a7"));

        //  参数：颜色栏显示颜色目录
        PieDataSet pieDataSet = new PieDataSet(sizes, "");

        pieDataSet.setColors(colors);

        //设置环内/环外显示百分比数据
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLinePart1Length(2F);


        PieData pieData = new PieData(pieDataSet);

        //  设置数据的大小
        pieData.setValueTextSize(25F);

        //设置所有DataSet内数据实体（百分比）的文本字体格式
        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int)value % (datas.length+1);
                return mess[index]+":" + datas[index]  ;
            }
        });
//        pieData.setValueFormatter(new PercentFormatter(mPieChart));


        //设置图例
        Legend legend_pie = mPieChart.getLegend();

        legend_pie.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend_pie.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend_pie.setTextColor(Color.BLACK);
        legend_pie.setYOffset(50f);
        legend_pie.setDrawInside(false);
        legend_pie.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);


        // 去除右下方描述
        Description description = new Description();
        description.setEnabled(false);
        mPieChart.setDescription(description);

        // 设置半径
        mPieChart.setHoleRadius(0f);

        // 半透明圈半径
        mPieChart.setTransparentCircleRadius(0f);

        //饼状图中间可以添加文字
        mPieChart.setDrawCenterText(true);

        // 初始旋转角度
        mPieChart.setRotationAngle(90);

        // 可以手动旋转
        mPieChart.setRotationEnabled(true);

        //  显示成百分比
        mPieChart.setUsePercentValues(true);

        //  设置pieChart图表上下左右的偏移，类似于外边距
        mPieChart.setExtraOffsets(40, 40, 40, 40);

        //  设置pieChart是否只显示饼图上百分比不显示文字,设置为true，下面属性才有效
        mPieChart.setDrawEntryLabels(false);


        //  添加数据
        mPieChart.setData(pieData);


        //将图表重绘以显示设置的属性和数据
        mPieChart.invalidate();
    }

    private void initView() {
        mPieChart = findViewById(R.id.pie_chart);

        toolbar = findViewById(R.id.tool_menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLJZ", "执行了: ");
                startActivity(new Intent(MainActivity.this,StackActivity.class));
            }
        });
    }
}
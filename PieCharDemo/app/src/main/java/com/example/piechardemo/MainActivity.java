package com.example.piechardemo;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import java.util.ArrayList;

/**
 * 参考文献：https://www.cnblogs.com/xuqp/p/11699754.html
 *         https://blog.csdn.net/zcmain/article/details/53611245
 *         https://blog.csdn.net/Honiler/article/details/80073883
 *
 * */
public class MainActivity extends AppCompatActivity {
    private PieChart mPieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //  大小
        ArrayList<PieEntry> sizes=new ArrayList<>();
        sizes.add(new PieEntry(45,"used"));
        sizes.add(new PieEntry(55,"left"));

        //  颜色
        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.parseColor("#FFBB33"));
        colors.add(Color.parseColor("#CCCCCC"));

        //  参数：颜色栏显示颜色目录
        PieDataSet pieDataSet  =  new PieDataSet(sizes,"");
        pieDataSet.setColors(colors);

        PieData pieData =  new PieData(pieDataSet);

        //  设置数据的大小
        pieData.setValueTextSize(25F);

        //  设置所有DataSet内数据实体（百分比）的文本字体格式
        pieData.setValueFormatter(new PercentFormatter());

        //  设置图例
        Legend legend_pie = mPieChart.getLegend();
        legend_pie.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend_pie.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend_pie.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend_pie.setTextColor(Color.GREEN);
        legend_pie.setDrawInside(false);
        legend_pie.setXEntrySpace(7f);
        legend_pie.setYEntrySpace(0f);
        legend_pie.setYOffset(0f);


        //  去除右下方描述
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

        //设置pieChart图表文本字体颜色,大小
        /*
            mPieChart.setEntryLabelColor(Color.GREEN);
            mPieChart.setEntryLabelTextSize(30f);
        */


        //  添加数据
        mPieChart.setData(pieData);


        //将图表重绘以显示设置的属性和数据
        mPieChart.invalidate();

    }

    private void initView() {
        mPieChart = findViewById(R.id.pie_chart);
    }
}

package com.example.competition_dislocation12;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
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
/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {

    /**属性相关帖子。
     * https://www.cnblogs.com/Sharley/p/8492893.html
     * https://blog.csdn.net/a8688555/article/details/80344159
     * https://www.cnblogs.com/r-decade/p/6241693.html
     * https://www.jianshu.com/p/6f8156ee0143
     * */
    private LineChart lc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lc = findViewById(R.id.lineChart);
        // 1. 获取一或多组Entry对象集合的数据
        // 模拟数据1
        List<Entry> data = new ArrayList<>();
        int[] ys1 = new int[] {19,19,16,20,21,21,23,19,19,16,18,15};

        for (int i = 0; i < 12; i++) {
            Random random = new Random();
            int number = random.nextInt(25 - 16 + 1) + 16;
            data.add(new Entry(i, number));
        }
        // 2. 分别通过每一组Entry对象集合的数据创建折线数据集
        LineDataSet lineDataSet1 = new LineDataSet(data, "最高温度");

        //折现的颜色
        lineDataSet1.setColor(Color.parseColor("#8f8f8f"));

        //折现点的颜色
        lineDataSet1.setCircleColor(Color.parseColor("#8f8f8f"));

        //设置折点实心显示
        lineDataSet1.setDrawCircleHole(false);

        //不显示坐标值
        lineDataSet1.setDrawValues(true);

        // 将每一组折线数据集添加到折线数据中
        LineData lineData = new LineData(lineDataSet1);

        //获取此图表的x轴
        XAxis xAxis = lc.getXAxis();

        //不显示格子的竖线
        xAxis.setDrawGridLines(false);

        //不显示X轴的标注
        //xAxis.setDrawLabels(false);

        //重新设置X轴的标注值
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(String.valueOf(i+1).concat("月"));
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));

        //不显示x轴轴线
        xAxis.setDrawAxisLine(false);

        //设置x轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        //Y轴右半部分不显示
        YAxis rightYAris = lc.getAxisRight();
        rightYAris.setEnabled(false);

        YAxis leftYAris = lc.getAxisLeft();

        //y轴左侧的轴线不显示
        leftYAris.setDrawAxisLine(false);

        //设置Y轴最低值，最高值，和每个值之间的间距
        leftYAris.setAxisMinimum(14F);
        leftYAris.setAxisMaximum(26F);

        //添加X轴限制线
         /*  LimitLine yLimitLine = new LimitLine(15F);
        yLimitLine.setLineColor(Color.parseColor("#8f8f8f"));
        leftYAris.addLimitLine(yLimitLine);*/


        //  取消拖动高亮线
        lc.setHighlightPerDragEnabled(false);

        leftYAris.setDrawZeroLine(false);

        //左下角图例
        Legend legend = lc.getLegend();
        legend.setEnabled(false);

        //去除右下方描述
        Description description = new Description();
        description.setEnabled(false);
        lc.setDescription(description);

        lc.setData(lineData);

    }
}
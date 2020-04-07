package com.example.radarchartdemo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.util.ArrayList;
import java.util.List;
/***
 *
 * 参考文献：https://blog.csdn.net/Honiler/article/details/90407390
 *         https://blog.csdn.net/Honiler/article/details/90441603
 *         https://blog.csdn.net/petterp/article/details/90115690
 *         https://blog.csdn.net/tyyj90/article/details/48230247
 * */
public class MainActivity extends AppCompatActivity {
    private RadarChart mRadarChart;
    private XAxis xAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //初始化雷达图
        initChart();
        //设置雷达图数据
        setData();
    }

    private void initView() {
        mRadarChart = findViewById(R.id.radar_chart);
    }

    private void initChart() {

        //设置web线的颜色(即就是外面包着的那个颜色)
        mRadarChart.setWebColorInner(Color.RED);

        //设置中心线颜色(也就是竖着的线条)
        mRadarChart.setWebColor(Color.GREEN);
        mRadarChart.setWebAlpha(100);

        xAxis = mRadarChart.getXAxis();

        //设置x轴标签字体颜色
        xAxis.setLabelCount(5, true);
        xAxis.setAxisMaximum(5f);
        xAxis.setTextSize(20f);
        xAxis.setAxisMinimum(0f);
        //自定义x轴标签，y轴同理
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                //这里只是自定义了标签的颜色，如果要使用自定义标签颜色，请更改布局文件为自定为RoadarCharts类
                mRadarChart.getXAxis().setTextColor(Color.parseColor("#3c3f41"));
                return super.getFormattedValue(value);
            }
        });

        YAxis yAxis = mRadarChart.getYAxis();

        //设置y轴的标签个数
        yAxis.setLabelCount(6, true);


        //设置y轴从0f开始
        yAxis.setAxisMinimum(0F);
        yAxis.setAxisMaximum(80F);

        /*启用绘制Y轴顶点标签，这个是最新添加的功能
         *
         */

        yAxis.setDrawTopYLabelEntry(true);
        //设置字体大小
        yAxis.setTextSize(15f);


        yAxis.setDrawTopYLabelEntry(true);

        //设置字体大小
        yAxis.setTextSize(15f);

        //设置字体颜色
        yAxis.setTextColor(Color.RED);

        //启用线条，如果禁用，则无任何线条
        mRadarChart.setDrawWeb(true);

        //禁用图例和图表描述
        mRadarChart.getLegend().setEnabled(false);
        mRadarChart.getDescription().setEnabled(false);
    }

    private void setData() {
        List<RadarEntry> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(new RadarEntry((float) (Math.random() * 100)));
            Log.d("CLJZ", "data is: "+list.get(i));
        }

        RadarDataSet set = new RadarDataSet(list, "Petterp");

        //禁用标签
        set.setDrawValues(false);

        //设置填充颜色
        set.setFillColor(Color.BLUE);

        //设置填充透明度
        set.setFillAlpha(40);

        //设置启用填充
        set.setDrawFilled(true);
        set.setDrawHighlightCircleEnabled(false);

        //设置点击之后标签是否显示圆形外围
        set.setDrawHighlightCircleEnabled(true);

        //设置点击之后标签圆形外围的颜色
        set.setHighlightCircleFillColor(Color.RED);

        //设置点击之后标签圆形外围的透明度
        set.setHighlightCircleStrokeAlpha(40);

        //设置点击之后标签圆形外围的半径
        set.setHighlightCircleInnerRadius(20f);

        //设置点击之后标签圆形外围内圆的半径
        set.setHighlightCircleOuterRadius(10f);


        List<RadarEntry> list1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list1.add(new RadarEntry((float) (Math.random() * 100)));
        }

        RadarDataSet set1 = new RadarDataSet(list1, "Petter");

        //禁用标签
        set1.setDrawValues(false);

        //设置填充颜色
        set1.setFillColor(Color.RED);

        //设置填充透明度
        set1.setFillAlpha(40);

        //设置启用填充
        set1.setDrawFilled(true);

        //设置点击之后标签是否显示圆形外围
        set1.setDrawHighlightCircleEnabled(true);

        //设置点击之后标签圆形外围的颜色
        set1.setHighlightCircleFillColor(Color.RED);

        //设置点击之后标签圆形外围的透明度
        set1.setHighlightCircleStrokeAlpha(40);

        //设置点击之后标签圆形外围的半径
        set1.setHighlightCircleInnerRadius(20f);

        //设置点击之后标签圆形外围内圆的半径
        set1.setHighlightCircleOuterRadius(10f);

        //添加两条数据
        RadarData  data = new RadarData(drawAngleCircle(),set,set1);

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
        for (int i = 0; i < 6; i++) {
            RadarEntry radarEntry = new RadarEntry(80F);
            //为每个数据设置一个图标
            radarEntry.setIcon(getResources().getDrawable(drawables[i]));
            yVals.add(radarEntry);
        }
        RadarDataSet ds = new RadarDataSet(yVals, "");
        // 不显示数据连线
        ds.setColors(Color.RED);
        // 不绘制数据值
        ds.setDrawValues(false);
        return ds;
    }
}
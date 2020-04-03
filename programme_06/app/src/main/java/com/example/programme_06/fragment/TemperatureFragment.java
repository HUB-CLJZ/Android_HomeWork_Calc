package com.example.programme_06.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.programme_06.R;
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
public class TemperatureFragment extends Fragment {
    private LineChart lc;
    private List<Entry> datas;
    private LineDataSet lineDataSet;
    private LineData lineData;

    public TemperatureFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temperature, container, false);
        lc = view.findViewById(R.id.lineChart);

        //添加模拟数据
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int number = random.nextInt(25 - 16 + 1) + 16;
            datas.add(new Entry(i, number));
        }

        //添加进折线
        lineDataSet = new LineDataSet(datas, "温度");
        lineDataSet.setColor(Color.parseColor("#101010"));
        lineDataSet.setDrawValues(true);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawValues(true);
        lineDataSet.setCircleColor(Color.parseColor("#8f8f8f"));

        //添加进入图表
        lineData = new LineData(lineDataSet);


        //设置x轴的属性
        XAxis xAxis = lc.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(90F);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(20, true);

        // 重新设置X轴的标注值
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i + 1).concat("月"));
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));

        //设置y轴右半轴属性
        YAxis rightYAris = lc.getAxisRight();
        rightYAris.setEnabled(false);

        //设置y轴左半轴属性
        YAxis leftYAris = lc.getAxisLeft();
        leftYAris.setDrawAxisLine(false);

        //设置Y轴最低值，最高值，和每个值之间的间距
        leftYAris.setAxisMinimum(15F);
        leftYAris.setAxisMaximum(25F);
        leftYAris.setLabelCount(6, true);
        leftYAris.setDrawZeroLine(false);

        //取消拖动高亮线
        lc.setHighlightPerDragEnabled(false);

        //左下角图例
        Legend legend = lc.getLegend();
        legend.setEnabled(false);

        //去除右下方描述
        Description description = new Description();
        description.setEnabled(false);
        lc.setDescription(description);

        //设置进入图表
        lc.setData(lineData);
        return view;
    }
}
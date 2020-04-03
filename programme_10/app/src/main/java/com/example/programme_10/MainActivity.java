package com.example.programme_10;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListTraditionalChinese;
    private ListView mListLenovoBuilding;
    private ListAdapter traditional_chinese_adapter;
    private ListAdapter lenovo_building_adapter;

    private List<String> traditional_chinese_time, traditional_chinese_distances;
    private List<String> lenovo_building_time, lenovo_building_distances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        traditional_chinese_time = new ArrayList<>();
        traditional_chinese_distances = new ArrayList<>();

        lenovo_building_time = new ArrayList<>();
        lenovo_building_distances = new ArrayList<>();

        //中医院站
        traditional_chinese_time.add("5分钟到达");
        traditional_chinese_time.add("6分钟到达");
        traditional_chinese_distances.add("距离站台100米");
        traditional_chinese_distances.add("距离站台1000米");


        //联想站
        lenovo_building_time.add("5分钟到达");
        lenovo_building_time.add("7分钟到达");
        lenovo_building_distances.add("距离站台200米");
        lenovo_building_distances.add("距离站台1200米");

        traditional_chinese_adapter = new ListAdapter(this, traditional_chinese_time, traditional_chinese_distances);
        lenovo_building_adapter = new ListAdapter(this, lenovo_building_time, lenovo_building_distances);

        mListLenovoBuilding.setAdapter(lenovo_building_adapter);
        mListTraditionalChinese.setAdapter(traditional_chinese_adapter);
    }

    private void initView() {
        mListTraditionalChinese = (ListView) findViewById(R.id.list_traditional_chinese);
        mListLenovoBuilding = (ListView) findViewById(R.id.list_lenovo_building);
    }

}

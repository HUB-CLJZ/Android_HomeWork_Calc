package com.example.recycleview1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.recycleview1.adapters.MoreTypeAdaper;
import com.example.recycleview1.beans.Datas;
import com.example.recycleview1.beans.MoreTypeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author admin
 */
public class Main2Activity extends AppCompatActivity {
    private RecyclerView mList;
    private List<MoreTypeBean> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mList = findViewById(R.id.recycle_view);
        mData = new ArrayList<>();
        //创建和设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);
        //创建适配器
        MoreTypeAdaper adapter = new MoreTypeAdaper(mData);
        initDatas();
        //设置适配器
        mList.setAdapter(adapter);
    }

    private void initDatas() {
        for (int i = 0; i < 3; i++) {
            MoreTypeBean data = new MoreTypeBean();
            data.pic = Datas.icons[i];
            data.type = i;
            mData.add(data);
        }
    }
}

package com.example.coordinatorlayoutdemo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.coordinatorlayoutdemo.adapters.ListViewAdapter;
import com.example.coordinatorlayoutdemo.beans.Datas;
import com.example.coordinatorlayoutdemo.beans.ItemBean;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mList;
    private List<ItemBean> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = findViewById(R.id.recycle_view);
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < Datas.icons.length; i++) {
            ItemBean datas = new ItemBean();
            datas.setIcon(Datas.icons[i]);
            datas.setTitle("我是第"+i+"个条目");
            mData.add(datas);
        }
        ListViewAdapter adapter = new ListViewAdapter(mData);
        mList.setAdapter(adapter);
        //设置RecycleView的样式，实现listView功能
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(linearLayoutManager);
    }
}

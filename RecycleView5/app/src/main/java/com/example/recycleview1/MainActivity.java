package com.example.recycleview1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recycleview1.adapters.GridViewAdapter;
import com.example.recycleview1.adapters.ListViewAdapter;
import com.example.recycleview1.adapters.StaggerAdapter;
import com.example.recycleview1.beans.Datas;
import com.example.recycleview1.beans.ItemBean;
import java.util.ArrayList;
import java.util.List;
/**
 * @author admin
 */
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
        showList(true,false);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < Datas.icons.length; i++) {
            ItemBean datas = new ItemBean();
            datas.setIcon(Datas.icons[i]);
            datas.setTitle("我是第"+i+"个条目");
            mData.add(datas);
        }

    }

    /*系统调用*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            //ListView的部分
            case R.id.list_view_vertical_stander:
                //isVertical是否是垂直，isReverse是否是反向
                Log.d(TAG, "点击了ListView里头的垂直标准");
                showList(true,false);
                break;
            case R.id.list_view_vertical_reverse:
                Log.d(TAG, "点击了ListView里头的垂直反向");
                showList(true,true);
                break;
            case R.id.list_view_horizontal_stander:
                Log.d(TAG, "点击了ListView里头的水平标准");
                showList(false,false);
                break;
            case R.id.list_view_horizontal_reverse:
                Log.d(TAG, "点击了ListView里头的水平反向");
                showList(false,true);
                break;

            //GridView部分
            case R.id.grid_view_vertical_stander:
                showGrid(true,false);
                break;
            case R.id.grid_view_vertical_reverse:
                showGrid(true,true);
                break;
            case R.id.grid_view_horizontal_stander:
                showGrid(false,false);
                break;
            case R.id.grid_view_horizontal_reverse:
                showGrid(false,true);
                break;

            //瀑布流部分
            case R.id.stagger_view_vertical_stander:
                showStagger(true,false);
                break;
            case R.id.stagger_view_vertical_reverse:
                showStagger(true,true);
                break;
            case R.id.stagger_view_horizontal_stander:
                showStagger(false,false);
                break;
            case R.id.stagger_view_horizontal_reverse:
                showStagger(false,true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showStagger(boolean isVertical, boolean isReverse) {
        //参数一，显示的行数，参数二显示方式
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,isVertical ? StaggeredGridLayoutManager.VERTICAL: StaggeredGridLayoutManager.HORIZONTAL);
        staggeredGridLayoutManager.setReverseLayout(isReverse);
        mList.setLayoutManager(staggeredGridLayoutManager);
        StaggerAdapter staggerAdapter = new StaggerAdapter(mData);
        mList.setAdapter(staggerAdapter);
    }

    private void showGrid(boolean isVertical, boolean isReverse) {
        //设置RecycleView的样式，实现listView功能
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        //设置水平还是垂直
        gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL:GridLayoutManager.HORIZONTAL);
        //设置正向反向显示
        gridLayoutManager.setReverseLayout(isReverse);
        mList.setLayoutManager(gridLayoutManager);

        //添加适配器
        GridViewAdapter adapter = new GridViewAdapter(mData);
        mList.setAdapter(adapter);
    }

    private void showList(boolean isVertical, boolean isReverse) {
        //设置RecycleView的样式，实现listView功能
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置水平还是垂直
        linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL:LinearLayoutManager.HORIZONTAL);
        //设置正向反向显示
        linearLayoutManager.setReverseLayout(isReverse);
        mList.setLayoutManager(linearLayoutManager);
        //添加适配器
        ListViewAdapter adapter = new ListViewAdapter(mData);
        mList.setAdapter(adapter);
    }
}
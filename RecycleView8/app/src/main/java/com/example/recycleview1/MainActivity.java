package com.example.recycleview1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.recycleview1.adapters.GridViewAdapter;
import com.example.recycleview1.adapters.ListViewAdapter;
import com.example.recycleview1.adapters.RecyclerViewBaseAdapter;
import com.example.recycleview1.adapters.StaggerAdapter;
import com.example.recycleview1.beans.Datas;
import com.example.recycleview1.beans.ItemBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mList;
    private List<ItemBean> mData;
    private RecyclerViewBaseAdapter mAdapt;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int index = Datas.icons.length - 1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //关联布局
        mList = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe);
        mData = new ArrayList<>();
        initData();
        addData();
    }
    /**
     * @description:用于下拉时添加数据
     * @author: CLJZ
     * @date: 2019/12/30  22:29
     * @param: []
     * @return: void
     */
    private void addData() {
        //设置下拉时圆圈的颜色（可以由多种颜色拼成）
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);

        //设置下拉时圆圈的背景颜色（这里设置成白色）
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //设置下拉刷新时的操作
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ItemBean datas = new ItemBean();
                        Random random = new Random(0);
                        datas.setIcon(Datas.icons[random.nextInt(index)]);
                        int addIndex = ++index;
                        datas.setTitle("我是第"+addIndex+"个条目");
                        mData.add(datas);
                        Toast.makeText(MainActivity.this, "刷新了一条数据", Toast.LENGTH_SHORT).show();
                        showList(true,false);
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1200);

            }
        });
    }

    private void initData() {
        for (int i = 0; i < Datas.icons.length; i++) {
            ItemBean datas = new ItemBean();
            datas.setIcon(Datas.icons[i]);
            datas.setTitle("我是第"+i+"个条目");
            mData.add(datas);
        }
        showList(true,false);
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
        mAdapt = new StaggerAdapter(mData);
        mAdapt.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, ("第"+position+"被点击"), Toast.LENGTH_SHORT).show();
            }
        });
        mList.setAdapter(mAdapt);

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
        mAdapt = new GridViewAdapter(mData);
        mAdapt.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, ("第"+position+"被点击"), Toast.LENGTH_SHORT).show();
            }
        });
        mList.setAdapter(mAdapt);
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
        mAdapt = new ListViewAdapter(mData);
        mAdapt.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, ("第"+position+"被点击"), Toast.LENGTH_SHORT).show();
            }
        });
        mList.setAdapter(mAdapt);
    }
}
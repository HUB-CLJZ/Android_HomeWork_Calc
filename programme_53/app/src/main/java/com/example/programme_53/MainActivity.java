package com.example.programme_53;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout mSwipeRefresh;
    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tv_show.setVisibility(View.VISIBLE);
                tv_show.setText("正在刷新");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv_show.setText("刷新完成");
                        mSwipeRefresh.setRefreshing(false);

                    }
                },3000);
            }

        });
    }

    private void initView() {
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        tv_show = (TextView) findViewById(R.id.tv_show);
    }
}

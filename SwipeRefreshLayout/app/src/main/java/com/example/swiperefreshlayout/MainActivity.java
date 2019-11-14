package com.example.swiperefreshlayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private SwipeRefreshLayout swipeRefreshLayout;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView1);
        swipeRefreshLayout = findViewById(R.id.swiptContainer);
        swipeRefreshLayout.setColorSchemeColors(android.R.color.holo_red_light,
                                                android.R.color.holo_blue_light,
                                                android.R.color.holo_green_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                textView.setText("正在刷新");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("刷新完成");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }
}

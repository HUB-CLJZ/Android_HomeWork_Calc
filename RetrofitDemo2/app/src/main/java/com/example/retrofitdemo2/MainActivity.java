package com.example.retrofitdemo2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.net.HttpURLConnection;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_getRequest;
    private RecyclerView recycler_view;

    private List<JsonResult> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_getRequest = findViewById(R.id.btn_getRequest);
        recycler_view = findViewById(R.id.recycler_view);

        btn_getRequest.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        System.out.println("按钮。。。。。");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tutefresh.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        Call<List<JsonResult>> task = api.getJson();
        task.enqueue(new Callback<List<JsonResult>>() {
            @Override
            public void onResponse(Call<List<JsonResult>> call, Response<List<JsonResult>> response) {

                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        mData = response.body();
                        //设置RecycleView的样式，实现listView功能
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);

                        recycler_view.setLayoutManager(linearLayoutManager);
                        //添加适配器
                        RecyclerAdaper adapter = new RecyclerAdaper(mData);
                        recycler_view.setAdapter(adapter);
                    }
            }
            @Override
            public void onFailure(Call<List<JsonResult>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fsilllllllllllll", Toast.LENGTH_SHORT).show();

            }
        });
    }
}


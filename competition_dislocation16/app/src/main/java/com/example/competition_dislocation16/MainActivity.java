package com.example.competition_dislocation16;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btn_open = (Button) findViewById(R.id.btn_open);

        btn_open.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_open:
                Log.d("CLJZ", "按钮有效: ");
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.mocky.io")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                API api = retrofit.create(API.class);

                Call<List<CarBean>> task = api.getData();

               task.enqueue(new Callback<List<CarBean>>() {
                   @Override
                   public void onResponse(Call<List<CarBean>> call, Response<List<CarBean>> response) {
                       if (response.code()== HttpURLConnection.HTTP_OK) {
                           Log.d("CLJZ", "onResponse: "+response.body().toString());
                       }
                   }

                   @Override
                   public void onFailure(Call<List<CarBean>> call, Throwable t) {

                   }
               });
                break;
        }
    }
}

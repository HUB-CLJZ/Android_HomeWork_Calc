package com.example.retrofitdemo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.net.HttpURLConnection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //定义一个Retrofit，并进行构建
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.asilu.com")
                        .build();
                //获取接口
                API api = retrofit.create(API.class);
                //调用请求
                Call<ResponseBody> task = api.getJson();
                task.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("CLJZ", "onResponse: " + response.code());
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            try {
                                Log.d("CLJZ", "json -----> : " + response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }


}

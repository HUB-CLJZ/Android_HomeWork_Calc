package com.example.getwebdata;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Map<String, Object>> list;
    private ListView listView;
    private String[] user_images;
    private String[] user_names;
    private String[] passwords;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.response_text);
        Button sendRequestGet = findViewById(R.id.send_request_get);
        Button sendRequestPost = findViewById(R.id.send_request_post);
        sendRequestGet.setOnClickListener(this);
        sendRequestPost.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request_get) {
            sendRequestWithOkHttpForGet();
            initDataList(user_images,user_names,passwords);
            // key值数组，适配器通过key值取value，与列表项组件一一对应
            String[] from = {"user_image","user_name","password"};
            // 列表项组件Id 数组
            int[] to = { R.id.user_image, R.id.user_name, R.id.password };
            /**
             * SimpleAdapter(Context context, List<?xtendMap<String?>> data, int resource, String[] from, int[] to)
             * context：activity界面类
             * data 数组内容是map的集合数据
             * resource 列表项文件
             * from map key值数组
             * to 列表项组件id数组
             * from与to一一对应，适配器绑定数据
             */
            final SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.view_iteam, from, to);
            listView.setAdapter(adapter);
        } else if (v.getId() == R.id.send_request_post) {
            sendRequestWithOkHttpForPost();
            initDataList(user_images,user_names,passwords);
            // key值数组，适配器通过key值取value，与列表项组件一一对应
            String[] from = {"user_image","user_name","password"};
            // 列表项组件Id 数组
            int[] to = { R.id.user_image, R.id.user_name, R.id.password };
            /**
             * SimpleAdapter(Context context, List<?xtendMap<String?>> data, int resource, String[] from, int[] to)
             * context：activity界面类
             * data 数组内容是map的集合数据
             * resource 列表项文件
             * from map key值数组
             * to 列表项组件id数组
             * from与to一一对应，适配器绑定数据
             */
            final SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.view_iteam, from, to);
            listView.setAdapter(adapter);
        }
    }
    private void sendRequestWithOkHttpForGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://106.52.109.143:15415/admin/getAll")
                            .build();
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        throw  new IOException("Unexpected code" + response);
                    }
                    String responseData = response.body().string();
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendRequestWithOkHttpForPost() {
        try {
            //创建OKHttpCLient
            OkHttpClient client = new OkHttpClient.Builder().build();
            //第二步创建RequestBody（Form表达）
            RequestBody body = new FormBody.Builder().add("username","admin").add("password","123456").build();
            //第三步创建Rquest
            Request request = new Request.Builder().url("http://106.52.109.143:15415/admin/getAll").post(body).build();
            //第四步创建call回调对象
            final Call call = client.newCall(request);
            //第五步发起请求dd
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("onFailure", e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    showResponse(responseData);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    count = jsonArray.length();
                    user_images = new String[count];
                    user_names = new String[count];
                    passwords = new String[count];
                    for (int i = 0; i < count; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String user_name = jsonObject.getString("user_name");
                        String password = jsonObject.getString("password");
                        String user_image = jsonObject.getString("user_image");
                        user_images[i] = user_image;
                        user_names[i] = user_name;
                        passwords[i] = password;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 初始化适配器需要的数据格式
     */
    private void initDataList(String[] user_images,String[]  user_names, String[] passwords) {
        list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap();
            map.put("user_image", user_images[i]);
            map.put("user_name", user_names[i]);
            map.put("password", passwords[i]);
            list.add(map);
        }
    }
}

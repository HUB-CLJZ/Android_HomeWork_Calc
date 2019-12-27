package com.example.getwebimage;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MyAdapter adapter;
    private ProgressDialog dialog;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private static final String PATH = "http://www.mocky.io/v2/5de5e06d2e0000780031fedc";
//    private static final String PATH = "http://10.0.2.2:8080/Project_1_war_exploded/studentInformation.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =  findViewById(R.id.my_list);
        dialog = new ProgressDialog(this);
        dialog.setTitle("提示！");
        dialog.setMessage("正在加载中...");
        /**
         * 开始获取数据
         */
        new MyTask().execute(PATH);

        /**
         * 每个item的点击事件
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "您点击了：" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * MyTask继承线程池AsyncTask用来网络数据请求、json解析、数据更新等操作。
     */
    class MyTask extends AsyncTask<String, Void, String> {
        /**
         * 数据请求前显示dialog。
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        /**
         * 在doInBackground方法中，做一些诸如网络请求等耗时操作。
         */
        @Override
        protected String doInBackground(String... params) {
            return RequestData();
        }

        /**
         * 在该方法中，主要进行一些数据的处理，更新。
         */
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                // 如果获取的result数据不为空，那么对其进行JSON解析。并显示在手机屏幕上。
                list = JSONAnalysis(result);
                adapter = new MyAdapter(MainActivity.this, list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            } else if (result == null) {
                Toast.makeText(MainActivity.this, "请求数据失败...",
                        Toast.LENGTH_LONG).show();
            }
            dialog.dismiss();
        }
    }

    /**
     * 网络数据请求
     * @return
     */
    public String RequestData() {
        String responseData = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(PATH)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw  new IOException("Unexpected code" + response);
            }
            responseData = response.body().string();
            Log.d("CLJZ", "RequestData: "+responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }


    /**
     * JSON解析
     * @param result
     * @return
     */
    public List<Map<String, Object>> JSONAnalysis(String result) {
        JSONArray array = null;
        try {
            array = new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < array.length(); i++) {
            JSONObject objectOne = array.optJSONObject(i);
            String user_name = objectOne.optString("user_name");
            String password = objectOne.optString("password");
            String user_image = objectOne.optString("user_image");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("user_name", user_name);
            map.put("password", password);
            map.put("user_image", user_image);
            list.add(map);
        }
        return list;
    }

}
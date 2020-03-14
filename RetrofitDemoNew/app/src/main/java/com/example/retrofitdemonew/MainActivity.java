package com.example.retrofitdemonew;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;

/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;

    private String URL_Weather = "https://api.asilu.com";
    private String URL_TaoBao = "https://suggest.taobao.com";
    private String URL_Music = "https://api.asilu.com";
    private Button btn_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        checkPermission();

    }

    private void initView() {
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 =  findViewById(R.id.btn_9);


        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1: {
                //定义一个Retrofit，并进行构建
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL_Weather)
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
            break;
            case R.id.btn_2: {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL_TaoBao)
                        .build();

                API api = retrofit.create(API.class);

                Call<ResponseBody> task = api.getData("utf-8", "上海");
                task.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            try {
                                Log.d("CLJZ", "onResponse: " + response.body().string());
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
            break;

            case R.id.btn_3: {
                Log.d("CLJZ", "按钮三被执行: ");
                //定义Retrofit，设置URL
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL_Music)
                        .build();
                //创建接口
                API api = retrofit.create(API.class);
                //调用接口方法
                Map<String, Object> map = new HashMap<>();
                map.put("type", "playlist");
                map.put("lrc", "0");
                map.put("id", "47201585");
                Call<ResponseBody> task = api.getMusic(map);
                task.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("CLJZ", "请求有响应: ");
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            Log.d("CLJZ", "请求成功: ");
                            try {
                                String str = response.body().string();
                                String data = unicodeToString(str);
                                Log.d("CLJZ", "数据为: " + data.replaceAll("\\\\", ""));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("CLJZ", "请求失败: ");
                    }
                });
            }
            break;
            case R.id.btn_4: {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL_Weather)
                        .build();

                API api = retrofit.create(API.class);
                String url = "/weather_v2";
                Call<ResponseBody> task = api.getJson(url);
                task.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            try {
                                Log.d("CLJZ", "使用URL: " + response.body().string());
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
            break;
            case R.id.btn_5: {
                Log.d("CLJZ", "按钮5被执行到了: ");
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:9102/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                API api = retrofit.create(API.class);
                Call<PostWithParms> task = api.getData("时间的朋友");
                task.enqueue(new Callback<PostWithParms>() {
                    @Override
                    public void onResponse(Call<PostWithParms> call, Response<PostWithParms> response) {
                        Log.d("CLJZ", "按钮5被执行到了: ");
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            Log.d("CLJZ", "请求成功: ");
                            Log.d("CLJZ", "onResponse: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<PostWithParms> call, Throwable t) {
                        Log.d("CLJZ", "请求失败: ");
                    }
                });
            }
            break;
            case R.id.btn_6: {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:9102/")
                        .build();

                API api = retrofit.create(API.class);
                Call<ResponseBody> task = api.getDataUri("/post/string?string=时间的朋友");
                task.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            try {
                                Log.d("CLJZ", "post请求url方式: " + response.body().string());
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
            break;
            case R.id.btn_7: {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:9102/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                API api = retrofit.create(API.class);

                CommitBean data = new CommitBean("100581", "评论很棒");
                Call<PostWithParms> task = api.setData(data);

                task.enqueue(new Callback<PostWithParms>() {
                    @Override
                    public void onResponse(Call<PostWithParms> call, Response<PostWithParms> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            Log.d("CLJZ", "按钮7评论内容: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<PostWithParms> call, Throwable t) {

                    }
                });
            }
            break;

            case R.id.btn_8: {

                //定义需要上传的文件，注意文件上传权限
                File file = new File("/sdcard/DCIM/bigPhoto.jpg");

                //设置上传的文件的类型
                MediaType mediaType = MediaType.parse("image/jpg");

                //创建请求体，参数一是上传文件类型，第二个参数为文件全路径
                RequestBody fileBody = RequestBody.create(mediaType, file);

                //请求体和文件名称封装成MultipartBody.Part
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileBody);

                //发送网络请求，上传文件
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:9102/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                API api = retrofit.create(API.class);
                Call<FileBean> task = api.postFile(part);

                task.enqueue(new Callback<FileBean>() {
                    @Override
                    public void onResponse(Call<FileBean> call, Response<FileBean> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            System.out.println("上传成功");
                        }
                    }

                    @Override
                    public void onFailure(Call<FileBean> call, Throwable t) {
                        System.out.println("相应失败");
                    }
                });
            }
            break;
            case R.id.btn_9: {
                //文件的路径
                String[] data = {"/sdcard/DCIM/menu.png","/sdcard/DCIM/menu1.png","/sdcard/DCIM/menu2.png"};
                //存储文件的集合
                List<MultipartBody.Part> list = new ArrayList<>();
                //添加到文件集合
                for (int i = 0; i < data.length; i++) {
                    //文件路径
                    File file = new File(data[i]);
                    //封装为请求体
                    RequestBody body = RequestBody.create(MediaType.parse("image/png"),file);
                    //请求体转化为MultipartBody.Part类型,第一个参数与服务器中的参数对应
                    MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), body);
                    //添加到集合
                    list.add(part);
                }
                //发起请求
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:9102/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                API api = retrofit.create(API.class);

                Call<FileBean> task = api.postFiles(list);

                task.enqueue(new Callback<FileBean>() {
                    @Override
                    public void onResponse(Call<FileBean> call, Response<FileBean> response) {
                        System.out.println("上传成功"+response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<FileBean> call, Throwable t) {
                        System.out.println("上传失败");
                    }
                });
            }
            break;
        }
    }

    //unicode转化为str
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    //权限申请
    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 5);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("CLJZ", "checkPermission: 已经授权！");
        }
    }
}
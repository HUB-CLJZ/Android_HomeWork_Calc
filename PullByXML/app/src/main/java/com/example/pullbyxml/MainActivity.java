package com.example.pullbyxml;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvCity;
    private ImageView ivIcon;
    private TextView tvWeather;
    private TextView tvTemp;
    private TextView tvWind;
    private TextView tvPm;
    private Map<String,String> map;
    private List<Map<String,String>> list;
    private String temp, weather, name, pm, wind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        InputStream is = this.getResources().openRawResource(R.raw.weather1);

        //使用Gson解析
        InputStream is = this.getResources().openRawResource(R.raw.weather2);
        try {
//            List<WeatherInfo> weatherInfos = WeatherService.getInfosFromXML(is);
            List<WeatherInfo> weatherInfos = WeatherService.getInfoFromJson(is);

            list = new ArrayList<>();

            for (WeatherInfo info: weatherInfos) {
                map = new HashMap<>();
                map.put("temp", info.getTemp());
                map.put("weather", info.getWeather());
                map.put("name", info.getName());
                map.put("pm", info.getPm());
                map.put("wind", info.getWind());
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "信息解析失败",Toast.LENGTH_SHORT).show();
        }

        getMap(1, R.drawable.ic_launcher_background);
    }

    private void initView() {
        tvCity = findViewById(R.id.tv_city);
        ivIcon = findViewById(R.id.iv_icon);
        tvWeather = findViewById(R.id.tv_weather);
        tvTemp = findViewById(R.id.tv_temp);
        tvWind = findViewById(R.id.tv_wind);
        tvPm = findViewById(R.id.tv_pm);

        findViewById(R.id.btn_bj).setOnClickListener(this);
        findViewById(R.id.btn_sh).setOnClickListener(this);
        findViewById(R.id.btn_gz).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.btn_bj:
                getMap(0,R.drawable.ic_launcher_background);
                break;

            case  R.id.btn_sh:
                getMap(1,R.drawable.ic_launcher_background);
                break;

            case  R.id.btn_gz:
                getMap(2,R.drawable.ic_launcher_background);
                break;
        }
    }

    private void getMap(int number, int iconNumber) {
        Map<String,String> cityMap = list.get(number);
        temp = cityMap.get("temp");
        weather = cityMap.get("weather");
        name = cityMap.get("name");
        pm = cityMap.get("pm");
        wind = cityMap.get("wind");

        tvCity.setText(name);
        tvWeather.setText(weather);
        tvTemp.setText(""+temp);
        tvWind.setText("风力:"+wind);
        tvPm.setText("pm:"+pm);
        ivIcon.setImageResource(iconNumber);
    }
}

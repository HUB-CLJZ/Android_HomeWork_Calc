package com.example.programme_14;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * 温度，相对湿度，二氧化碳的样式与第六题相同
 */
public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    //请求的网站
    private String URL_TianQi = "https://tianqiapi.com/";

    //最高温度和最低温度
    private List<String> height_temp = new ArrayList<>();
    private List<String> low_temp = new ArrayList<>();

    //记录周
    private List<String> weeks = new ArrayList<>();

    //解析响应的json
    private Gson gson = new Gson();
    private TextView mTvCurrentTemp;
    private TextView mTvDayTemp;
    private ImageView mIvRefresh;

    //折线表
    private LineChart lineChart;

    //生活指数
    private List<String> lefe_level = new ArrayList<>();
    private List<String> lefe_desc = new ArrayList<>();

    private TextView mTvLevelSun;
    private TextView mTvDescSun;
    private TextView mTvLevelBloodGlucose;
    private TextView mTvDescBloodGlucose;
    private TextView mTvLevelClothes;
    private TextView mTvDescClothes;
    private TextView mTvLevelWashCar;
    private TextView mTvDescWashCar;
    private TextView mTvLevelAirPollution;
    private TextView mTvDescAirPollution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //初始化温度
        setTemp(URL_TianQi);

        //刷新数据的方法
        setClick();
    }

    private void setLifeIndex(String response_text) {
        TianQiApiWeather tianQiApiWeather = gson.fromJson(response_text, TianQiApiWeather.class);

        for (TianQiApiWeather.DataBean.IndexBean index : tianQiApiWeather.getData().get(0).getIndex()) {
            lefe_desc.add(index.getDesc());
            lefe_level.add(index.getLevel());
        }

        System.out.println(lefe_level.toString());
        System.out.println(lefe_desc.toString());


        mTvLevelSun.setText(lefe_level.get(0));
        mTvLevelBloodGlucose.setText(lefe_level.get(2));
        mTvLevelClothes.setText(lefe_level.get(3));
        mTvLevelWashCar.setText(lefe_level.get(4));
        mTvLevelAirPollution.setText(lefe_level.get(5));

        mTvDescSun.setText(lefe_desc.get(0));
        mTvDescBloodGlucose.setText(lefe_desc.get(2));
        mTvDescClothes.setText(lefe_desc.get(3));
        mTvDescWashCar.setText(lefe_desc.get(4));
        mTvDescAirPollution.setText(lefe_desc.get(5));
    }

    private void setWeekTempShow(String response_text) {
        TianQiApiWeather tianQiApiWeather = gson.fromJson(response_text, TianQiApiWeather.class);
        //获取今天的温度情况
        for (TianQiApiWeather.DataBean data : tianQiApiWeather.getData()) {
            low_temp.add(data.getTem2().replace("℃", ""));
            height_temp.add(data.getTem1().replace("℃", ""));
            weeks.add(data.getWeek());
        }

        System.out.println("最高温度:" + height_temp.toString());
        System.out.println("最低温度:" + low_temp.toString());

        setLineChart();
    }

    private void setLineChart() {
        List<Entry> min_temp = new ArrayList<>();
        List<Entry> max_temp = new ArrayList<>();

        //获取到的接口数据，加入图表
        for (int i = 0; i < low_temp.size(); i++) {
            max_temp.add(new Entry(i, Integer.parseInt(height_temp.get(i))));
            min_temp.add(new Entry(i, Integer.parseInt(low_temp.get(i))));
        }

        //分别通过每一组Entry对象集合的数据创建折线数据集
        LineDataSet lowLineDataSet = new LineDataSet(min_temp, "最低温度");
        LineDataSet heightLineDataSet = new LineDataSet(max_temp, "最高温度");

        //设置折线的颜色
        lowLineDataSet.setColor(Color.parseColor("#4572a7"));
        heightLineDataSet.setColor(Color.parseColor("#aa4643"));


        //设置折点颜色
        lowLineDataSet.setCircleColor(Color.parseColor("#4572a7"));
        heightLineDataSet.setCircleColor(Color.parseColor("#aa4643"));

        //设置折点实心显示
        lowLineDataSet.setDrawCircleHole(false);
        heightLineDataSet.setDrawCircleHole(false);

        //显示值
        lowLineDataSet.setDrawValues(true);
        lowLineDataSet.setDrawValues(true);

        // 将每一组折线数据集添加到折线数据中
        LineData lineData = new LineData(lowLineDataSet, heightLineDataSet);

        //x轴的设置
        XAxis xAxis = lineChart.getXAxis();

        //不显示格子的竖线
        xAxis.setDrawGridLines(false);

        //重新设置X轴轴线的值
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weeks));

        //设置x轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.TOP);

        //X轴显示每个月份
        xAxis.setLabelCount(7, true);

        //Y轴右半部分不显示
        YAxis rightYAris = lineChart.getAxisRight();
        rightYAris.setEnabled(false);

        //不显示轴线
        rightYAris.setDrawAxisLine(false);
        YAxis leftYAris = lineChart.getAxisLeft();

        //y轴左侧的轴线不显示
        leftYAris.setDrawLabels(false);
        leftYAris.setDrawAxisLine(false);

        //设置Y轴最低值，最高值，和每个值之间的间距
        leftYAris.setLabelCount(5, true);

        //取消拖动高亮线
        lineChart.setHighlightPerDragEnabled(false);

        //左下角图例
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        //去除右下方描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);

        //添加进图表
        lineChart.invalidate();
        lineChart.setData(lineData);
    }

    private void setClick() {
        mIvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height_temp.clear();
                low_temp.clear();
                //重新获取温度
                setTemp(URL_TianQi);
                Toast.makeText(MainActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    /**
     * @description:设置显示当天的天气
     * @author: CLJZ
     * @date: 2020/4/3  16:10
     * @param: [请求的结果]
     * @return: void
     */
    private void setDayTempShow(String response_text) {
        TianQiApiWeather tianQiApiWeather = gson.fromJson(response_text, TianQiApiWeather.class);
        //获取今天的温度情况
        String current_temp = tianQiApiWeather.getData().get(0).getTem();
        mTvCurrentTemp.setText(current_temp);

        //获取今天的温度区间
        String day_temp = tianQiApiWeather.getData().get(0).getTem2() + "-" +
                tianQiApiWeather.getData().get(0).getTem1();
        mTvDayTemp.setText("今天:" + day_temp);
    }

    /**
     * @description:获取相应的内容
     * @author: CLJZ
     * @date: 2020/4/3  16:10
     * @param: [请求的地址]
     * @return: void
     */
    private void setTemp(String url) {
        Call<ResponseBody> task = getCall(url);
        //获取请求的结果
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    try {
                        String response_text = response.body().string();
                        //设置一天的天气
                        setDayTempShow(response_text);
                        //设置一周内最高温度和最低温度
                        setWeekTempShow(response_text);

                        //设置生活指数
                        setLifeIndex(response_text);
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

    /**
     * @description:获取请求的方法
     * @author: CLJZ
     * @date: 2020/4/3  16:08
     * @param: [请求的url]
     * @return: retrofit2.Call<okhttp3.ResponseBody>
     */
    private Call<ResponseBody> getCall(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获取接口
        API api = retrofit.create(API.class);
        //调用接口方法
        Map<String, Object> map = new HashMap<>();
        map.put("version", "v1");
        map.put("appid", "77255231");
        map.put("city", "无锡");
        map.put("appsecret", "ewM2umJd");
        return api.getWeather(map);
    }

    private void initView() {
        mTvCurrentTemp = findViewById(R.id.tv_current_temp);
        mTvDayTemp = findViewById(R.id.tv_day_temp);
        mIvRefresh = findViewById(R.id.iv_refresh);
        lineChart = findViewById(R.id.lineChart);
        mTvLevelSun =  findViewById(R.id.tv_level_sun);
        mTvDescSun = findViewById(R.id.tv_desc_sun);
        mTvLevelBloodGlucose =  findViewById(R.id.tv_level_blood_glucose);
        mTvDescBloodGlucose =  findViewById(R.id.tv_desc_blood_glucose);
        mTvLevelClothes =  findViewById(R.id.tv_level_clothes);
        mTvDescClothes =  findViewById(R.id.tv_desc_clothes);
        mTvLevelWashCar =  findViewById(R.id.tv_level_wash_car);
        mTvDescWashCar =  findViewById(R.id.tv_desc_wash_car);
        mTvLevelAirPollution =  findViewById(R.id.tv_level_air_pollution);
        mTvDescAirPollution =  findViewById(R.id.tv_desc_air_pollution);

        toolbar = findViewById(R.id.tool_menu);
    }
}
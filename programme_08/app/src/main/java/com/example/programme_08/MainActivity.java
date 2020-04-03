package com.example.programme_08;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvData;
    private TextView tvVehicle;
    private TextView tvSitting;
    private TextView tvCar1State;
    private Switch swCar1;
    private TextView tvCar2State;
    private Switch swCar2;
    private TextView tvCar3State;
    private Switch swCar3;
    private ImageView ivAnimation;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    //选择的天
    private int selectDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        calendar = Calendar.getInstance(Locale.CHINA);

        //设置初始日期
        String data = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月" + (calendar.get(Calendar.DAY_OF_MONTH));

        tvData.setText(data);
        //设置初始状态控件是否可编辑
        day_is_odd(calendar);

        //监听的方法，处理各种监听
        listeningMothod();

        //动画的效果
        playFrame();
    }

    private void initView() {
        tvData = findViewById(R.id.tv_data);
        tvVehicle = findViewById(R.id.tv_vehicle);
        tvSitting = findViewById(R.id.tv_sitting);
        tvCar1State = findViewById(R.id.tv_car1_state);
        swCar1 = findViewById(R.id.sw_car1);
        tvCar2State = findViewById(R.id.tv_car2_state);
        swCar2 = findViewById(R.id.sw_car2);
        tvCar3State = findViewById(R.id.tv_car3_state);
        swCar3 = findViewById(R.id.sw_car3);
    }

    //处理奇数，偶数日控件是否可编辑,以及显示的内容
    private void day_is_odd(Calendar calendar) {
        int selectDay = calendar.get(Calendar.DAY_OF_MONTH);
        Log.d("CLJZ", "日期为: "+selectDay);
        if (selectDay%2 == 0) {
            swCar1.setChecked(false);
            swCar1.setEnabled(false);

            swCar2.setEnabled(true);

            swCar3.setChecked(false);
            swCar3.setEnabled(false);

            tvVehicle.setText("双号出行车辆：2");
        } else {
            swCar1.setEnabled(true);

            swCar2.setChecked(false);
            swCar2.setEnabled(false);

            swCar3.setEnabled(true);
            tvVehicle.setText("单号出行车辆：1、3");
        }
    }

    private void listeningMothod() {
        //设置选择之后的日期
        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        //记录选择之后的天
                        selectDay = calendar.get(Calendar.DAY_OF_MONTH);
                        Log.d("CLJZ", "选择的是哪一天: "+selectDay);

                        //判断选择之后的日期是单号还是双号
                        day_is_odd(calendar);

                        //设置选择之后的日期
                        tvData.setText(calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月" + (calendar.get(Calendar.DAY_OF_MONTH)));


                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        //三个switch的监听
        swCar1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvCar1State.setText("开");
                } else {
                    tvCar1State.setText("停");
                }
            }
        });

        swCar2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvCar2State.setText("开");
                } else {
                    tvCar2State.setText("停");
                }
            }
        });

        swCar3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvCar3State.setText("开");
                } else {
                    tvCar3State.setText("停");
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void playFrame() {
        ivAnimation = findViewById(R.id.iv_animation);
        //动画的可控区域
        AnimationDrawable animationDrawable = new AnimationDrawable();
        //设置为动画的可控区域
        ivAnimation.setBackground(animationDrawable);
        //添加播放的图片,并设置间隔时间
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.green),1000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.red),1000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.yello),1000);
        //设置循环播放
        animationDrawable.setOneShot(false);
        //开始播放动画
        animationDrawable.start();
    }
}

package com.example.programme_09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
//自定义对话框课本62页
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;

    //四个充值按钮
    private Button mRecharge1;
    private Button mRecharge2;
    private Button mRecharge3;
    private Button mRecharge4;

    //四个充值选择框
    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;
    private CheckBox mCheckBox4;

    //账户的余额
    private TextView mTvBalance1;
    private TextView mTvBalance2;
    private TextView mTvBalance3;
    private TextView mTvBalance4;

    //金额异常时的颜色
    private LinearLayout mLayout_item_1;
    private LinearLayout mLayout_item_2;
    private LinearLayout mLayout_item_3;
    private LinearLayout mLayout_item_4;

    //对金额进行监听的计时器
    private Timer mTimer = null;

    //批量充值文档
    private TextView tv_batch;

    //数据库帮助类
    private MyHelper myHelper;

    //记录选择的字段
    private int[] index;

    //传递数据的Intent
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  初始化的方法
        initView();

        //  监听事件的方法
        spa_mothod();

        myHelper = new MyHelper(this);
        SQLiteDatabase data = myHelper.getWritableDatabase();
        data.close();

        //对四个按钮的监听
        mRecharge1.setOnClickListener(this);
        mRecharge2.setOnClickListener(this);
        mRecharge3.setOnClickListener(this);
        mRecharge4.setOnClickListener(this);
    }

    //读取数据库中的初始值
    private void readData() {
        myHelper = new MyHelper(this);
        SQLiteDatabase database = myHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM car ",null);
        int count = 1;
        if (cursor!= null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                if (count == 1 ) {
                    mTvBalance1.setText(cursor.getString(4));
                } else if (count == 2) {
                    mTvBalance2.setText(cursor.getString(4));
                } else if (count == 3) {
                    mTvBalance3.setText(cursor.getString(4));
                } else if (count == 4) {
                    mTvBalance4.setText(cursor.getString(4));
                }
                count++;
            }
        }
        cursor.close();
        database.close();
    }

    private void spa_mothod() {
        //对金额异常的事件进行监听
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //切换到主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        readData();
                        if (Integer.parseInt(mTvBalance1.getText().toString()) < 10) {
                            mLayout_item_1.setBackgroundColor(Color.parseColor("#ffcc00"));
                        } else {
                            mLayout_item_1.setBackgroundColor(Color.parseColor("#ffffff"));
                        }

                        if (Integer.parseInt(mTvBalance2.getText().toString()) < 10) {
                            mLayout_item_2.setBackgroundColor(Color.parseColor("#ffcc00"));
                        }   else {
                            mLayout_item_2.setBackgroundColor(Color.parseColor("#ffffff"));
                        }

                        if (Integer.parseInt(mTvBalance3.getText().toString()) < 10) {
                            mLayout_item_3.setBackgroundColor(Color.parseColor("#ffcc00"));
                        } else {
                            mLayout_item_3.setBackgroundColor(Color.parseColor("#ffffff"));
                        }

                        if (Integer.parseInt(mTvBalance4.getText().toString()) < 10) {
                            mLayout_item_4.setBackgroundColor(Color.parseColor("#ffcc00"));
                        } else {
                            mLayout_item_4.setBackgroundColor(Color.parseColor("#ffffff"));
                        }
                    }
                });
            }
        }, 1000, 1000);

        //批量充值的监听
        tv_batch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //有一个选择
                if ( (mCheckBox1.isChecked() || mCheckBox2.isChecked() || mCheckBox3.isChecked() || mCheckBox4.isChecked())) {
                    boolean[] datas = new boolean[4];
                    //   传递的复选框的状态
                    datas[0] = mCheckBox1.isChecked();
                    datas[1] = mCheckBox2.isChecked();
                    datas[2] = mCheckBox3.isChecked();
                    datas[3] = mCheckBox4.isChecked();

                    //   跳转intent
                    intent = new Intent(MainActivity.this,RechargeActivity.class);
                    intent.putExtra("datas",datas);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "您没有选择", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {

        //四个充值按钮的初始化
        mRecharge1 = findViewById(R.id.btn_recharge_1);
        mRecharge2 = findViewById(R.id.btn_recharge_2);
        mRecharge3 = findViewById(R.id.btn_recharge_3);
        mRecharge4 = findViewById(R.id.btn_recharge_4);

        //四个充值选择框的初始化
        mCheckBox1 = findViewById(R.id.checkbox_choose_1);
        mCheckBox2 = findViewById(R.id.checkbox_choose_2);
        mCheckBox3 = findViewById(R.id.checkbox_choose_3);
        mCheckBox4 = findViewById(R.id.checkbox_choose_4);

        //四个账户余额的初始化
        mTvBalance1 = findViewById(R.id.tv_banner_1);
        mTvBalance2 = findViewById(R.id.tv_banner_2);
        mTvBalance3 = findViewById(R.id.tv_banner_3);
        mTvBalance4 = findViewById(R.id.tv_banner_4);

        //金额异常时的背景颜色的初始化
        mLayout_item_1 = findViewById(R.id.item_1);
        mLayout_item_2 = findViewById(R.id.item_2);
        mLayout_item_3 = findViewById(R.id.item_3);
        mLayout_item_4 = findViewById(R.id.item_4);

        //批量充值文本框的监听
        tv_batch = findViewById(R.id.tv_batch);

        //图片，用于初始化数据中的数据
        toolbar = findViewById(R.id.tool_menu);
    }

    //点击按钮的监听
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_recharge_1) {
            //向充值界面传递是第几个条目
            intent = new Intent(MainActivity.this, RechargeActivity.class);
            index = new int[1];
            index[0] = 1;
            intent.putExtra("data",index);
            startActivity(intent);
        } else if (id == R.id.btn_recharge_2) {
            Intent intent = new Intent(MainActivity.this, RechargeActivity.class);
            index = new int[1];
            index[0] = 2;
            intent.putExtra("data",index);
            startActivity(intent);
        } else if (id == R.id.btn_recharge_3) {
            intent = new Intent(MainActivity.this, RechargeActivity.class);
            index = new int[1];
            index[0] = 3;
            intent.putExtra("data",index);
            startActivity(intent);
        } else if (id == R.id.btn_recharge_4) {
            intent = new Intent(MainActivity.this, RechargeActivity.class);
            index = new int[1];
            index[0] = 4;
            intent.putExtra("data",index);
            startActivity(intent);
        } else if (id == R.id.tool_menu) {
            myHelper = new MyHelper(this);
            SQLiteDatabase database = myHelper.getWritableDatabase();
            database.delete("car",null,null);
            database.close();

            myHelper = new MyHelper(this);
            database.close();
            readData();
        }
    }
}

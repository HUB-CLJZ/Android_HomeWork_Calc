package com.example.bianchen_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private Spinner mSpinner;
    private String[] str = {"1","2","3"};
    private Toolbar mToolbar;
    private DrawerLayout mDL;
    private MyHelper helper = new MyHelper(this);
    private TextView mBalance;
    private EditText mReCharge;
    private Button mBtn_recharge;
    private Button mBtn_query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        init();
        //创建适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, str);
        //关联适配器
        mSpinner.setAdapter(adapter);
        //可以设置默认显示的选项，角标是从0开始的偶，小心角标越界
        mSpinner.setSelection(0);
        //监听事件的初始化
        initListener();

    }

    private void initListener() {
        //下拉框选项的监听
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String  num = ((TextView)view).getText().toString();
                SQLiteDatabase db = getDB();
                Cursor cursor = db.query("car3",null,"number=?",new String[]{num},null,null,null);
                if (cursor.moveToNext()) {
                    String balance = cursor.getString(cursor.getColumnIndex("balance"));
                    mBalance.setText(balance+"元");
                }
                //释放资源
                closeResource(db,cursor);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //监听toolbar的导航点击
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开左侧边栏
                mDL.openDrawer(Gravity.LEFT);
            }
        });

        //充值金额的监听
        mReCharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String data = s.toString();

                if(data.length() > 0) {
                    if (data.charAt(0) == '0'&& (!data.equals(""))) {
                        mReCharge.setText("");
                        Toast.makeText(MainActivity.this, "输入金额非法！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //充值按钮的点击事件
        mBtn_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得输入的金额
                String money = mReCharge.getText().toString();
                if (money.equals("")){
                    Toast.makeText(MainActivity.this, "输入金额不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    //得到数据库的连接
                    SQLiteDatabase db = getDB();
                    //获得当前的车号
                    String num = mSpinner.getSelectedItem().toString();
                    //更新数据
                    ContentValues contentValues = new ContentValues();
                    //获得当前车号的余额
                    Cursor cursor = db.query("car3",null,"number=?",new String[]{num},null,null,null);
                    String balance = "";
                    if (cursor.moveToNext()) {
                        balance = cursor.getString(cursor.getColumnIndex("balance"));
                    }
                    contentValues.put("balance",Integer.valueOf(balance) +Integer.valueOf(money) );
                    db.update("car3",contentValues,"number=?",new String[]{num});
                    //当前页面也更新数据
                    mBalance.setText(Integer.valueOf(balance) +Integer.valueOf(money)+"元");
                    Toast.makeText(MainActivity.this, "充值成功！", Toast.LENGTH_SHORT).show();
                    //把充值记录添加进history表
                    contentValues = new ContentValues();
                    contentValues.put("number",num);
                    contentValues.put("recharge",money);
                    //将当前时间格式化后传入数据库里
                    //首先设置北京时区，消除时间差
                    TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
                    TimeZone.setDefault(timeZone);
                    long time = System.currentTimeMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date = new Date(time);
                    String his_time = sdf.format(date);

                    contentValues.put("time",his_time);
                    db.insert("history",null,contentValues);
                    closeResource(db,cursor);
                }
            }
        });
        //查询按钮的点击事件
        mBtn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,HistoryActivity.class));
            }
        });
    }


    //初始化控件
    private void init() {
        mSpinner = findViewById(R.id.spin);
        mToolbar = findViewById(R.id.toolBar);
        mDL = findViewById(R.id.dl);
        mBalance = findViewById(R.id.show_balance);
        mReCharge = findViewById(R.id.et_recharge);
        mBtn_recharge = findViewById(R.id.btn_recharge);
        mBtn_query = findViewById(R.id.btn_query);
    }
    //得到数据库连接对象
     public SQLiteDatabase getDB() {
         SQLiteDatabase db = helper.getWritableDatabase();
         return db;
     }
     //关闭数据库
    public void closeResource(SQLiteDatabase db, Cursor cursor) {
        if (db!= null) {
            db.close();
        }
        if (cursor != null) {
            cursor.close();
        }
    }
}

package com.example.bianchen_1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private Spinner spinner;
    private String[] data = {"时间升序","时间降序"};
    private ListView mLv;
    private TableLayout mTab;
    private Button mBtn_query;
    private List<Car> cars = new ArrayList<>();
    private MyHelper helper = new MyHelper(this);
    private  boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //初始化控件
        init();
        //创建适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,data);
        //关联适配器
        spinner.setAdapter(adapter);
        spinner.setSelection(1);

        //初始化点击事件
        initListener();
    }

    //查询按钮点击事件
    private void initListener() {
       //查询按钮点击事件
        mBtn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //让视图可视
                mLv.setVisibility(View.VISIBLE);
                mTab.setVisibility(View.VISIBLE);
            }
        });
        //下拉框监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mLv.removeAllViewsInLayout();
                String value = ((TextView)view).getText().toString();
                Toast.makeText(HistoryActivity.this, value, Toast.LENGTH_SHORT).show();
                if (value.equals("时间降序")) {
                    flag = true;
                } else {
                    flag = false;
                }
                //初始化适配器
                initAdapter();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initAdapter() {

        //获取数据源。
        //得到数据库连接对象
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("history",null,null,null,null,null,null);
        //如果集合里有以前的数据，清除
        cars.clear();
        while (cursor.moveToNext()) {
            int number = cursor.getInt(cursor.getColumnIndex("number"));
            String recharge = cursor.getString(cursor.getColumnIndex("recharge"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            Car car = new Car();
            car.setNumber(number);
            car.setRecharge(recharge);
            car.setTime(time);
            cars.add(car);
        }
        //flag为true，说明当前为倒叙
        if (flag) {
            System.out.println("flag" + flag);
            List<Car> rev_cars = new ArrayList<>();
            for (int i = cars.size()-1; i >=0; i--) {
                rev_cars.add(cars.get(i));
            }
            cars = rev_cars;
        }

        //释放资源
        cursor.close();
        db.close();

        //创建适配器
        MyAdapter adapter2 = new MyAdapter(cars,HistoryActivity.this);
        //关联适配器
        mLv.setAdapter(adapter2);
        //更新数据
        adapter2.notifyDataSetChanged();
    }

    private void init() {
        spinner = findViewById(R.id.spinner);
        mLv = findViewById(R.id.listView);
        mTab = findViewById(R.id.tab);
        mBtn_query = findViewById(R.id.btn_ask);
    }
}

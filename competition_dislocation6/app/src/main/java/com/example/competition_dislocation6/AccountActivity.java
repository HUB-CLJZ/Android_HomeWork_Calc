package com.example.competition_dislocation6;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
/**
 * @author admin
 */
public class AccountActivity extends AppCompatActivity {
    private Toolbar tool_menu;
    //车辆号单选框的一些变量
    private Spinner depSpinner;
    private String  car_number;
    //数据库操作的变量
    private MyHelplerDataBase myHelplerDataBase;
    //需要用到的控件
    private TextView tv_balance;
    private TextView tv_car_number;
    private Button btn_query;
    private Button btn_recharge;
    private EditText et_query_money;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        myHelplerDataBase = new MyHelplerDataBase(this);
        initView();
        et_query_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("执行了....");
                String data = s.toString();
                if(data.length() > 0) {
                    if (data.charAt(0) == '0'&& (!data.equals(""))) {
                        et_query_money.setText("");
                        Toast.makeText(AccountActivity.this, "输入金额非法！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        listeningMethods();
    }

    private void listeningMethods() {
        tool_menu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this,MainActivity.class));
            }
        });

        depSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                car_number  = (String) parent.getItemAtPosition(position);
                Toast.makeText(AccountActivity.this, "选择了"+car_number+"号车", Toast.LENGTH_SHORT).show();
                int money = Money_sum_count(Integer.parseInt(car_number));
                tv_balance.setText("账户余额："+money+"元");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase writeDatabase = myHelplerDataBase.getWritableDatabase();
                ContentValues values = new ContentValues();
                Integer money = Integer.parseInt(et_query_money.getText().toString());
                Integer number = Integer.parseInt(car_number);
                values.put("car_recharge_amount",money);
                values.put("car_number",number);
                TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
                TimeZone.setDefault(timeZone);
                SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//设置日期格式
                String data = df.format(new Date());
                values.put("logtime",data);
                writeDatabase.insert("recharge_information",null,values);
                Toast.makeText(AccountActivity.this, "信息已添加", Toast.LENGTH_SHORT).show();
                writeDatabase.close();
                //更新余额
                int money_sum = Money_sum_count(Integer.parseInt(car_number));
                tv_balance.setText("账户余额："+money_sum+"元");
            }
        });


        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this,ShowActivity.class));
            }
        });
    }

    private void initView() {
        tool_menu = findViewById(R.id.tool_menu);
        depSpinner = findViewById(R.id.depSpinner);
        tv_balance = findViewById(R.id.balance);
        tv_car_number = findViewById(R.id.car_number);
        btn_query = findViewById(R.id.btn_query);
        btn_recharge = findViewById(R.id.btn_recharge);
        et_query_money = findViewById(R.id.et_query_money);
    }

    private int Money_sum_count(int car_number) {
        SQLiteDatabase readableDatabase = myHelplerDataBase.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select * from recharge_information where car_number="+car_number,null);
        car_number = 0;
        if (cursor!= null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Integer count = cursor.getInt(2);
                System.out.println(count);
                car_number += count;
            }
        }
        readableDatabase.close();
        return car_number;
    }
}
package com.example.competition_dislocation15;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class RechargeActivity extends AppCompatActivity {
    private EditText etRecharge;
    private Button btnRecharge;
    private Button btnCancel;

    private TextView showCarNumber;
    //数据库帮助类
    private MyHelper myHelper;

    //计算金额
    private int money = 0;

    //多个充值记录时的金额
    private int moneys[] = new int[4];

    //单一选择的记录
    private int[] data;

    //多个选择的记录
    private boolean[] datas;

    //记录需要充值的id
    private int[] id = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        //初始化界面
        initView();

        //单一界面的数据
        Intent intent = getIntent();

        //获取单个选择时候传递的id
        data = intent.getIntArrayExtra("data");

        //获取单个选择时候传递的id
        datas = intent.getBooleanArrayExtra("datas");

        if (datas != null) {
            //记录多选的id
            for (int i = 0; i < 4; i++) {
                if (datas[i]) {
                    int count = i + 1;
                    id[i] = count;
                }
            }
            //读取点击充值按钮时的值
            readsData();
        } else {
            //读取点击批量充值时的值
            readData();
        }

        etRecharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String data = s.toString();
                if(data.length() > 0) {
                    if (data.charAt(0) == '0'&& (!data.equals(""))) {
                        etRecharge.setText("");
                        Toast.makeText(RechargeActivity.this, "输入金额非法！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //多个选择时候的写入
                if (datas != null) {
                    for (int i = 0; i < id.length; i++) {
                        myHelper = new MyHelper(RechargeActivity.this);
                        //写入输入的数据
                        SQLiteDatabase database = myHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        moneys[i] += Integer.parseInt(etRecharge.getText().toString());
                        Log.d("my", "充值了多少: " + moneys[i]);
                        values.put("balance", moneys[i]);
                        database.update("car", values, "_id=?", new String[]{id[i] + ""});
                        database.close();
                        Toast.makeText(RechargeActivity.this, "充值成功", Toast.LENGTH_SHORT).show();
                    }
                    //单个选择的时候的输入
                } else {
                    myHelper = new MyHelper(RechargeActivity.this);
                    //写入输入的数据
                    SQLiteDatabase database = myHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    money += Integer.parseInt(etRecharge.getText().toString());
                    values.put("balance", money);
                    database.update("car", values, "_id=?", new String[]{data[0]+""});
                    database.close();
                    Toast.makeText(RechargeActivity.this, "充值成功", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //读取选择为多个时的数据库初始数据
    private void readsData() {
        myHelper = new MyHelper(RechargeActivity.this);
        SQLiteDatabase readableDatabase = myHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM car ", null);
        String car_name = "";
        int index = 0;
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                if (datas[index]) {
                    //拼接多个数据的标题
                    car_name += " " + cursor.getString(2);
                    showCarNumber.setText(car_name);
                    //记录多个数据的金额
                    moneys[index] = Integer.parseInt(cursor.getString(4));
                }
                index++;
            }
        }
        cursor.close();
        readableDatabase.close();
    }
    //读取选择为单个时的数据库初始数据
    private void readData() {
        myHelper = new MyHelper(RechargeActivity.this);
        SQLiteDatabase readableDatabase = myHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM car where _id=" + data[0], null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                //记录数据库里的金额
                money = Integer.parseInt(cursor.getString(4));
                //设置标题显示的车辆信息
                showCarNumber.setText(cursor.getString(2));
            }
        }
        cursor.close();
        readableDatabase.close();
    }

    private void initView() {
        etRecharge = findViewById(R.id.et_recharge);
        btnRecharge = findViewById(R.id.btn_recharge);
        btnCancel = findViewById(R.id.btn_cancel);
        showCarNumber = findViewById(R.id.showCarNum);
    }
}

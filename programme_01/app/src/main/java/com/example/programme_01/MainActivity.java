package com.example.programme_01;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TextView mTvBannerNumber;
    private Spinner mTvCarNumber;
    private EditText mTvMoneyNumber;
    private Button mBtnQuery;
    private Button mBtnRecharge;
    private CarDatabaseHelper carDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        carDatabaseHelper = new CarDatabaseHelper(this);

        //默认显示1号金额
        Integer sum = queryByCar(mTvCarNumber.getSelectedItem().toString());
        mTvBannerNumber.setText(String.valueOf(sum));

        //对输入金额进行检查，0~999
        mTvMoneyNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String data = s.toString();
                if (data.length() > 0) {
                    if (data.charAt(0) == '0' && (!data.equals(""))) {
                        mTvMoneyNumber.setText("");
                        Toast.makeText(MainActivity.this, "输入金额非法！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 添加方法，添加金额到指定的车牌号
     */
    private void recharge() {
        SQLiteDatabase addDatabase = carDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("car_number", mTvCarNumber.getSelectedItem().toString());
        values.put("balance", mTvMoneyNumber.getText().toString());
        addDatabase.insert("car_information", null, values);
        Toast.makeText(this, "充值成功", Toast.LENGTH_SHORT).show();
        addDatabase.close();
    }

    /**
     * 查询方法，返回查询车牌号的金额
     */
    private Integer queryByCar(String number) {
        int sum = 0;
        SQLiteDatabase queryDatabase = carDatabaseHelper.getReadableDatabase();
        Cursor cursor = queryDatabase.rawQuery("SELECT * FROM car_information WHERE car_number=?", new String[]{number});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                sum += cursor.getInt(1);
            }
        }
        queryDatabase.close();
        return sum;
    }

    private void initView() {
        mTvBannerNumber = (TextView) findViewById(R.id.tv_banner_number);
        mTvCarNumber = (Spinner) findViewById(R.id.tv_car_number);
        mTvMoneyNumber = (EditText) findViewById(R.id.tv_money_number);
        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mBtnRecharge = (Button) findViewById(R.id.btn_recharge);
        mBtnQuery.setOnClickListener(this);
        mBtnRecharge.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                //输入框的处理
                Integer sum = queryByCar(mTvCarNumber.getSelectedItem().toString());
                mTvBannerNumber.setText(String.valueOf(sum));
                break;
            case R.id.btn_recharge:
                recharge();
                break;
            default:
                break;
        }
    }
}
package com.example.programme_31;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_title;
    private EditText et_content;
    private EditText et_phone;
    private Button btn_submit;
    private MyHelper myHelper = new MyHelper(this);
    private TextView tv_opinion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        tv_opinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OpinionActivity.class));
            }
        });

    }

    private void initView() {
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        et_phone = findViewById(R.id.et_phone);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        tv_opinion = findViewById(R.id.tv_opinion);
        tv_opinion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                writeData();

                break;
            default:
                break;
        }
    }

    private void writeData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //写入输入的数据
            SQLiteDatabase database = myHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", et_title.getText().toString());
            contentValues.put("content", et_content.getText().toString());
            contentValues.put("phone", et_phone.getText().toString());
            LocalDateTime time = LocalDateTime.now();
            String date = time.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日hh:mm"));
            Log.d("CLJZ", "date: " + date);
            contentValues.put("commit_time", date);
            long result = database.insert("feedback", null, contentValues);
            if (result != -1) {
                Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "写入失败", Toast.LENGTH_SHORT).show();
            }
            database.close();
        }
    }
}

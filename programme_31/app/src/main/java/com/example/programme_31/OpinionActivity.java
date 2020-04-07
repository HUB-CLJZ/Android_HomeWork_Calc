package com.example.programme_31;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class OpinionActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private ListView list;
    private MyAdaper myAdaper;
    private MyHelper myHelper = new MyHelper(this);
    private List<String> mTitles = new ArrayList<>();
    private List<String> mContexts = new ArrayList<>();
    private List<String> mTimes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);
        initView();
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SQLiteDatabase database = myHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT *FROM feedback", null);

        System.out.println("大小为" + cursor.getCount());

        if (cursor.getCount() <= 0 || cursor==null) {
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String title =  cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String time = cursor.getString(cursor.getColumnIndex("commit_time"));
                mTitles.add(title);
                mContexts.add(content);
                mTimes.add(time);
            }
            cursor.close();
            database.close();
        }

        myAdaper = new MyAdaper(this,mTitles,mContexts,mTimes);
        list.setAdapter(myAdaper);
    }

    private void initView() {
        toolBar = findViewById(R.id.toolBar);
        list = findViewById(R.id.list);
    }
}

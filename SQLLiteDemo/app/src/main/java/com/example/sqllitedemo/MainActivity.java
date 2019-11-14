package com.example.sqllitedemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    MyHelper myHelper;
    private EditText mEtname;
    private EditText mEtphone;
    private TextView mTvShow;
    private Button mBtnAdd;
    private Button mBtnQuery;
    private Button mBtnUpdata;
    private Button mBtnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper = new MyHelper(this);
        init();
    }

    private void init() {
        mEtname = findViewById(R.id.mEtname);
        mEtphone = findViewById(R.id.mEtphone);
        mBtnAdd = findViewById(R.id.mBtnAdd);
        mBtnQuery = findViewById(R.id.mBtnQuery);
        mBtnUpdata = findViewById(R.id.mBtnUpdata);
        mBtnDelete = findViewById(R.id.mBtnDelete);
        mTvShow = findViewById(R.id.tv_show);

        mBtnAdd.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mBtnUpdata.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name, phone;
        SQLiteDatabase db;
        ContentValues values;

        switch (view.getId()){
            case R.id.mBtnAdd:
                name = mEtname.getText().toString();
                phone = mEtphone.getText().toString();
                db = myHelper.getWritableDatabase();
                values  = new ContentValues();
                values.put("name",name);
                values.put("phone",phone);
                db.insert("information",null,values);
                Toast.makeText(this, "信息已添加", Toast.LENGTH_SHORT).show();
                db.close();
                break;

            case R.id.mBtnQuery:
                db = myHelper.getReadableDatabase();
                Cursor cursor = db.query("information",null,null,null,null,null,null);
                if (cursor.getCount() == 0) {
                    mTvShow.setText("");
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                } else {
                    cursor.moveToFirst();
                    String str = "Name:"+cursor.getString(1) + "------Tel:"+cursor.getString(2);
                    mTvShow.setText(str);
                }

                while (cursor.moveToNext()) {
                    mTvShow.append("\n"+"Name:"+cursor.getString(1)+";Tel:"+cursor.getString(2));
                }
                cursor.close();
                db.close();
                break;

            case R.id.mBtnUpdata:
                db = myHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("phone",phone = mEtphone.getText().toString());
                db.update("information",values,"name=?",new String[]{mEtname.getText().toString()});
                Toast.makeText(this, "信息已修改", Toast.LENGTH_SHORT).show();
                db.close();
                break;

            case R.id.mBtnDelete:
                db = myHelper.getWritableDatabase();
                db.delete("information",null,null);
                Toast.makeText(this, "信息已被删除", Toast.LENGTH_SHORT).show();
                db.close();
                break;
        }
    }
}

class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context) {
        super(context,"itcast.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20),phone VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

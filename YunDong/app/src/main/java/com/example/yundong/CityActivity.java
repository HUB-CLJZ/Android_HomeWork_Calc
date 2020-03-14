package com.example.yundong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityActivity extends AppCompatActivity implements View.OnClickListener {
    private DBHelper mDbHelper;
    private EditText mEdtCity,mEdtCode;
    private TextView mTvRowid;
    private ListView mListView;
    private List<Map<String,Object>> mListdata;
    SimpleAdapter mSimpleAdapter;
    private int mCurSelectedId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_city);



        mTvRowid = findViewById(R.id.textView_city_rowid);
        mEdtCity = findViewById(R.id.editText_city_name);
        mEdtCode = findViewById(R.id.editText_city_code);

        mListView = findViewById(R.id.listView_city);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String,Object> map = mListdata.get(i);
                mEdtCity.setText( map.get("name").toString());
                mEdtCode.setText(map.get("code").toString());
                mTvRowid.setText(map.get("id").toString());
                mCurSelectedId = Integer.parseInt(map.get("id").toString());
            }
        });

        findViewById(R.id.button_sql_insert).setOnClickListener(this);
        findViewById(R.id.button_sql_delete).setOnClickListener(this);
        findViewById(R.id.button_sql_query).setOnClickListener(this);
        findViewById(R.id.button_sql_modify).setOnClickListener(this);

        mDbHelper = new DBHelper(this);

        mListdata = new ArrayList<Map<String,Object>>();
        mSimpleAdapter = new SimpleAdapter(this,mListdata,R.layout.listview_city_item,
                new String[]{"id","name","code"},new int[]{R.id.lvcity_txt_id,R.id.lvcity_txt_name,R.id.lvcity_txt_code});
        mListView.setAdapter(mSimpleAdapter);
        queryCity();//查询数据，以更新listview中的显示
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.button_sql_insert:
                insertCity();
                break;
            case R.id.button_sql_modify:
                modifyCity();
                break;
            case R.id.button_sql_query:
                queryCity();
                break;
            case R.id.button_sql_delete:
                deleteCity();
                break;
        }
    }

    void insertCity(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",mEdtCity.getText().toString());
        values.put("code",mEdtCode.getText().toString());
        long id = db.insert("citycode",null,values);
        db.close();

        queryCity();//添加后，重新查询数据，以更新listview中的显示；本种方式效率低，仅演示数据库功能用
    }

    void modifyCity(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",mEdtCity.getText().toString());
        values.put("code",mEdtCode.getText().toString());
        db.update("citycode",values,"_id=?",new String[]{Integer.toString(mCurSelectedId)});
        db.close();
        queryCity();//修改后，重新查询数据，以更新listview中的显示；本种方式效率低，仅演示数据库功能用
    }

    void deleteCity(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete("citycode","_id=?",new String[]{Integer.toString(mCurSelectedId)});
        db.close();

        queryCity();//删除后，重新查询数据，以更新listview中的显示；本种方式效率低，仅演示数据库功能用
    }

    void queryCity(){
        mListdata.clear();//重置数据为空，重新查询得到
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query("citycode",null,null,null,null,null,null);
        if(cursor.getCount()==0){
            Toast.makeText(this,"没有城市代码数据",Toast.LENGTH_SHORT).show();
            return;
        }
        cursor.moveToFirst();
        do{
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("id", cursor.getInt(0));
            map1.put("name", cursor.getString(1));
            map1.put("code", cursor.getInt(2));
            mListdata.add(map1);
        }while (cursor.moveToNext());
        cursor.close();
        db.close();
        mSimpleAdapter.notifyDataSetChanged();
    }
}

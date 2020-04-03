package com.example.programme_09;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(@Nullable Context context) {
        super(context, "carInfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table car(_id integer primary key autoincrement,number integer,carNum,carMas,balance)");
        db.execSQL("insert into car values(null,1,'辽A10001','张三','100')");
        db.execSQL("insert into car values(null,2,'辽A10002','李四','99')");
        db.execSQL("insert into car values(null,3,'辽A10003','王五','103')");
        db.execSQL("insert into car values(null,4,'辽A10004','赵六','1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
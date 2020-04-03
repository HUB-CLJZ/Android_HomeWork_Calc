package com.example.programme_03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(@Nullable Context context) {
        super(context, "car5.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table car3(_id integer primary key autoincrement,number integer,balance)");
        db.execSQL("insert into car3 values(null,1,55)");
        db.execSQL("insert into car3 values(null,2,0)");
        db.execSQL("insert into car3 values(null,3,0)");

        db.execSQL("create table history(_id integer primary key autoincrement,number integer,recharge,time)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

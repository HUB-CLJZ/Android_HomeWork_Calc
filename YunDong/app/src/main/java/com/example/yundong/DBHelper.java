package com.example.yundong;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context,"data.db",null,1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE citycode(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), code INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}

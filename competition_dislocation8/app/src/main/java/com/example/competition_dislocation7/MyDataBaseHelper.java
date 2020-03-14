package com.example.competition_dislocation7;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    public MyDataBaseHelper(Context context) {
        super(context, "road.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table road_table(_id integer primary key autoincrement," +
                "crossroads integer," +
                "red_lamp integer," +
                "yello_lamp integer," +
                "green_lamp integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

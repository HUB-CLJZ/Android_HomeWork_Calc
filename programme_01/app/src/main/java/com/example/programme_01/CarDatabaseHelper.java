package com.example.programme_01;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class CarDatabaseHelper extends SQLiteOpenHelper {

    public CarDatabaseHelper(Context context) {
        super(context, "car.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //表,保存车辆信息,包括余额和车号
        String SQL = "CREATE TABLE car_information(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "balance INTEGER," +
                "car_number INTEGER)";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

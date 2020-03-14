package com.example.competition_dislocation6;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MyHelplerDataBase extends SQLiteOpenHelper {
    public MyHelplerDataBase(Context context) {
        super(context, "car.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //表1,保存车辆信息,包括余额和车号
        String SQL1 = "CREATE TABLE car_information(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                      "balance INTEGER," +
                      "car_number INTEGER)";
        db.execSQL(SQL1);
        //表2,保存充值记录,包括车辆号、充值金额、时间。
        String SQL2 = "CREATE TABLE recharge_information(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                      "car_number INTEGER," +
                      "car_recharge_amount INTEGER," +
                      "logtime VARCHAR(8)"+
                      ")";
        db.execSQL(SQL2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
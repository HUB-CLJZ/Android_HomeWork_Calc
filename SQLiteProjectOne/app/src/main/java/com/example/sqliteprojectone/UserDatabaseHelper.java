package com.example.sqliteprojectone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
/**
  * @Package:        com.example.sqliteprojectone
  * @ClassName:      UserDatabaseHelper
  * @Description:    创建数据库的类
  * @Author:         CLJZ
  * @CreateDate:     2019/12/5 9:24
  * @UpdateUser:     CLJZ
  * @UpdateDate:     2019/12/5 9:24
  * @UpdateRemark:   更新说明：
  * @Version:        1.0
 */
public class UserDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "provider.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME_USER = "user";
    public static final String FIELD_USER_NAME = "user_name";
    public static final String FIELD_SEX = "sex";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_AGE = "age";
    public static final String FIELD_PASSWORD = "password";

    public UserDatabaseHelper(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建数据库的语句
        String createSql = "create table " +
                TABLE_NAME_USER +
                "(" + FIELD_ID +
                " integer primary key autoincrement," +
                FIELD_USER_NAME +
                " varchar(30)," +
                FIELD_PASSWORD +
                " varchar(32)," +
                FIELD_SEX + " " +
                "varchar(5)," +
                FIELD_AGE +
                " integer)";
        sqLiteDatabase.execSQL(createSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

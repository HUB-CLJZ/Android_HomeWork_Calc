package com.example.sqliteprojectone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
/**
  * @Package:        com.example.sqliteprojectone
  * @ClassName:      UserDao
  * @Description:    实现增删查改方法
  * @Author:         CLJZ
  * @CreateDate:     2019/12/5 9:37
  * @UpdateUser:     CLJZ
  * @UpdateDate:     2019/12/5 9:37
  * @UpdateRemark:   更新说明：
  * @Version:        1.0
 */
public class UserDao implements IUserDao {
    private final UserDatabaseHelper mUserDatabaseHelper;

    public UserDao(Context context) {
        mUserDatabaseHelper = new UserDatabaseHelper(context);
    }


    @Override
    public long addUser(User user) {
        SQLiteDatabase writeDatabase = mUserDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //添加值
        values.put(UserDatabaseHelper.FIELD_AGE,user.getAge());
        values.put(UserDatabaseHelper.FIELD_PASSWORD,user.getPassWord());
        values.put(UserDatabaseHelper.FIELD_SEX,user.getSex());
        values.put(UserDatabaseHelper.FIELD_USER_NAME,user.getUsername());
        //添加进入数据库中的表
        long result = writeDatabase.insert(UserDatabaseHelper.TABLE_NAME_USER,
                null,values );
        writeDatabase.close();
        return result;
    }

    @Override
    public int delUserById(int id) {
        SQLiteDatabase readableDatabase = mUserDatabaseHelper.getReadableDatabase();
        int result = readableDatabase.delete(UserDatabaseHelper.TABLE_NAME_USER,
                     UserDatabaseHelper.FIELD_ID,new String[]{id + ""});
        readableDatabase.close();
        return result;
    }

    @Override
    public int updateUser(User user) {
        SQLiteDatabase readableDatabase = mUserDatabaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        if (!TextUtils.isEmpty(user.getPassWord())) {
            values.put(UserDatabaseHelper.FIELD_PASSWORD,user.getPassWord());
        }
        if(!TextUtils.isEmpty(user.getSex())) {
            values.put(UserDatabaseHelper.FIELD_SEX,user.getSex());
        }
        if(!TextUtils.isEmpty(user.getUsername())) {
            values.put(UserDatabaseHelper.FIELD_USER_NAME,user.getUsername());
        }
        if(user.getAge() != 0) {
            values.put(UserDatabaseHelper.FIELD_AGE,user.getAge());
        }

        int updata = readableDatabase.update(UserDatabaseHelper.TABLE_NAME_USER,
                     values,UserDatabaseHelper.FIELD_ID,new String[]{user.get_id()+""});
        readableDatabase.close();
        return updata;
    }

    @Override
    public User getUserById(int id) {
        SQLiteDatabase readableDatabase = mUserDatabaseHelper.getReadableDatabase();
        String queueSQL = "select *from " + UserDatabaseHelper.TABLE_NAME_USER +
                          " where " + UserDatabaseHelper.FIELD_ID + "=?";
        Cursor cursor = readableDatabase.rawQuery(queueSQL,new String[] {id+""});
        User user = null;
        if (cursor.moveToNext()) {
            user = new User();
            int userid = cursor.getInt(cursor.getColumnIndex(UserDatabaseHelper.FIELD_ID));
            user.set_id(userid);

            String userName = cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.FIELD_USER_NAME));
            user.setUsername(userName);
            int userAge = cursor.getInt(cursor.getColumnIndex(UserDatabaseHelper.FIELD_AGE));
            user.setAge(userAge);
            String password = cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.FIELD_PASSWORD));
            user.setPassWord(password);
            String sex = cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.FIELD_SEX));
            user.setSex(sex);
        }
        readableDatabase.close();
        return user;
    }

    @Override
    public List<User> listUser() {
        SQLiteDatabase readableDatabase = mUserDatabaseHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query(UserDatabaseHelper.TABLE_NAME_USER,
                null,null,null,
                null,null,null);
        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            User user = new User();
            int userId = cursor.getInt(cursor.getColumnIndex(UserDatabaseHelper.FIELD_ID));
            user.set_id(userId);
            String userName = cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.FIELD_USER_NAME));
            user.setUsername(userName);
            int userAge = cursor.getInt(cursor.getColumnIndex(UserDatabaseHelper.FIELD_AGE));
            user.setAge(userAge);
            String password = cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.FIELD_PASSWORD));
            user.setPassWord(password);
            String sex = cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.FIELD_SEX));
            user.setSex(sex);
            users.add(user);
        }
        readableDatabase.close();
        return users;
    }
}

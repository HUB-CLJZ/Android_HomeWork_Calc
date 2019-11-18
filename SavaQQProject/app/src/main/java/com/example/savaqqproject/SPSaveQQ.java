package com.example.savaqqproject;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
public class SPSaveQQ {

    public static boolean savaUserInfo(Context context, String number, String password) {
        @SuppressLint("WrongConstant") SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userName",number);
        editor.putString("pwd",password);
        editor.commit();
        return true;
    }

    public static Map<String, String> getUserInfo(Context context) {
        @SuppressLint("WrongConstant") SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_APPEND);
        String number = sp.getString("userName",null);
        String password = sp.getString("paw",null);
        Map<String ,String > userMap = new HashMap<>();
        userMap.put("number",number);
        userMap.put("password",password);
        return userMap;
    }

}

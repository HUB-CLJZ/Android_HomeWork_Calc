package com.example.calc;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
public class FileSaveResult {

    public static boolean savaResultInfo(Context context, String result) {
        SharedPreferences sp = context.getSharedPreferences("result",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("result",result);
        editor.commit();
        return true;
    }

    public static Map<String, String> getResultInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("result", Context.MODE_PRIVATE);
        String result = sp.getString("result",null);
        Map<String ,String > userMap = new HashMap<>();
        userMap.put("result",result);
        return userMap;
    }
}
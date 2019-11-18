package com.example.savaqqproject;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileSaveQQ {
    //保存信息到文件
    public static boolean savaUserInfo(Context context, String number, String password) {
        try {
            FileOutputStream fos = context.openFileOutput("data.txt",Context.MODE_APPEND);
            fos.write((number+":"+password).getBytes());
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //从文件中读取信息
    public static Map<String ,String > getUserInfo(Context context) {
        String content = "";
        try {
            FileInputStream fis = context.openFileInput("data.txt");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            content = new String(buffer);
            Map<String ,String > userMap = new HashMap<>();
            String[] infos = content.split(":");
            userMap.put("number",infos[0]);
            userMap.put("password",infos[1]);
            fis.close();
            return userMap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

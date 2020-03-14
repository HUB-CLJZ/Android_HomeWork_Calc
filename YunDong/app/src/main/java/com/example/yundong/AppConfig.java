/*
    APP配置类，APP中经常用到的一些字符串、参数设定等，在此类中集中放置
    本类内都为静态成员
 */
package com.example.yundong;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AppConfig {
    //常量定义
    public static final String FILENAME_DEVINFO_TXT = "devlistinfo.txt";

    public  static final String KEYSTR_APPCFG_FILENAME = "config";

    public  static final String KEYSTR_AUTOSAVENAME = "autosavename";
    public  static final String KEYSTR_USERNAME = "username";
    public  static final String KEYSTR_USERPASSWD = "userpasswd";

    //音乐播放CMD定义
    public static final int MUSIC_PLAY  = 21;
    public static final int MUSIC_PAUSE = 22;
    public static final int MUSIC_STOP = 23;



    //后台线程CMD定义
    public static final int WORKTHREAD_UPDATE_TIANQI = 51;

    //发送给主窗口的CMD定义
    public static final int MAIN_UPDATE_TIANQI = 101;
    public static final int MAIN_SCREEN_SHOT= 102;//截屏CMD定义

    ///参赛变量定义
    public static boolean Config_AutoSaveUsername = false;
    //运动免打扰开关
    public static boolean Sport_NO_CALL = false;

    //保存图片至/data/data/包名/files目录中
    public static void saveImage(Context context, String filename, Bitmap bitmap){
        try {
            FileOutputStream fos = context.openFileOutput(filename,context.MODE_PRIVATE);
            //bitmap转文件对象
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取图片从/data/data/包名/files目录中
    public static Bitmap getImage(Context context, String filename){
        Bitmap bitmap = null;
        try {
            FileInputStream fis = context.openFileInput(filename);
            bitmap = BitmapFactory.decodeStream(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * unicode转中文
     * @param str
     * @return
     * @author yutao
     * @date 2017年1月24日上午10:33:25
     */
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch+"" );

        }

        return str;

    }

    //方法定义
    public  static void testhttpGET(){

        try {
            //步骤1：创建连接对象。
            String urlstr_tianqiapi = "https://api.asilu.com/weather/";
            String urlstr_baidu = "https://www.baidu.com";
            URL url = new URL(urlstr_baidu);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //步骤2：设置参数
            connection.setRequestMethod("GET");//设置请求方式
            connection.setConnectTimeout(20000); //设置连接超时 单位毫秒
            connection.setReadTimeout(20000);    //设置读取超时 单位毫秒
            //connection.setRequestProperty("accept", "*/*");

            //步骤3：建立连接
            connection.connect();
            //步骤4：根据响应码，判断连接是否成功，如果成果开始下载数据
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到输入流，该流是服务器向客户端发来的数据流
                InputStream inputStream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                //缓冲逐行读取
                String line;
                StringBuilder sb=new StringBuilder();
                while ( ( line = bufferedReader.readLine() ) != null ) {
                    sb.append(line);
                }
                bufferedReader.close();
                reader.close();
                inputStream.close();
                Log.d("YDHL","result============="+sb.toString());
            }
            connection.disconnect();
        }catch (Exception e){
            //注意，java.net包中的很多类方法会抛出异常，这里统一处理异常
            e.printStackTrace();
        }
    }

    //方法定义
    public  static String testhttpPOST(){
        String result=null;
        try {
            //步骤1：创建连接对象。
            String urlstr_tianqiapi = "https://api.asilu.com/weather/";
            String urlstr_baidu = "https://www.baidu.com";
            URL url = new URL(urlstr_tianqiapi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //步骤2：设置参数
            connection.setRequestMethod("POST");//设置请求方式
            connection.setDoOutput(true);//允许写出
            connection.setDoInput(true);//允许读入
            connection.setUseCaches(false);//不使用缓存
            connection.setConnectTimeout(20000); //设置连接超时 单位毫秒
            connection.setReadTimeout(20000);    //设置读取超时 单位毫秒
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");//设置参数类型是json格式

            //步骤3：建立连接
            connection.connect();

            //步骤4：向服务器写数据
            String body = "{\"cityid\":\"101010100\",\"date\":\"2020-01-14\"}";//101010100是北京的代码
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();

            //根据响应码，判断连接是否成功，如果成果开始下载数据
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到输入流，该流是服务器向客户端发来的数据流
                InputStream inputStream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                //缓冲逐行读取
                String line;
                StringBuilder sb=new StringBuilder();
                while ( ( line = bufferedReader.readLine() ) != null ) {
                    sb.append(line);
                }
                bufferedReader.close();
                reader.close();
                inputStream.close();
                result = sb.toString();
                Log.d("YDHL","result============="+sb.toString());
            }
            connection.disconnect();
        }catch (Exception e){
            //注意，java.net包中的很多类方法会抛出异常，这里统一处理异常
            e.printStackTrace();
        }
        return result;
    }
}


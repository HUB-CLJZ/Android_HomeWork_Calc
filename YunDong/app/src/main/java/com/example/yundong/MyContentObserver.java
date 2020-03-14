package com.example.yundong;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

public class MyContentObserver extends ContentObserver {
    private Handler  mHandler;
    private ContentResolver mResolver;
    //构造方法，获得主窗口的对象引用，与主窗口通信用
    public MyContentObserver(Handler handler, ContentResolver resolver) {
        super(handler);
        mHandler = handler;
        mResolver = resolver;
        Log.d("YDHL","MyContentObserver start");
    }
    //自定义的释放资源方法，注销观察者后调用，防止隐形持有造成内存泄露
    public void myRelease(){
        mHandler = null;
        mResolver = null;
    }

    //回调方法：里面会传入监听的uri信息
    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        if(uri.equals(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)) {
            Log.d("YDHL", uri.toString());
            dealMediaContentChange(uri);
        }
        if(uri.equals(MediaStore.Images.Media.INTERNAL_CONTENT_URI)){
            Log.d("YDHL", uri.toString());
            dealMediaContentChange(uri);
        }
    }
    //媒体库变化处理方法
    private void dealMediaContentChange(Uri uri) {
        Cursor cursor = null;
        String[] QUERY_MEDIA_PRJECTIONS = {
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.DATE_TAKEN,
                MediaStore.Images.ImageColumns.DATE_ADDED
        };
        try {
            // 数据改变时，查询数据库中最后加入的一条数据
            cursor = mResolver.query(uri, QUERY_MEDIA_PRJECTIONS, null, null,
                    MediaStore.Images.ImageColumns.DATE_ADDED + " desc limit 1");
            if (cursor == null) {
                return;
            }
            if (!cursor.moveToFirst()) {
                return;
            }
            int dataIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            int dateAddedIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_ADDED);
            // 处理获取到的第一行数据
            isScreenShotFile(cursor.getString(dataIndex), cursor.getLong(dateAddedIndex));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
    //判断是否截屏图片
    private void isScreenShotFile(String path, long dateAdded) {
        long duration = 0;
        long step = 100;
        Log.d("YDHL", "图片名:"+path+" ; 添加时间："+dateAdded);
        // 时间判断
        if (Math.abs(System.currentTimeMillis() / 1000 - dateAdded) > 1) {
            Log.d("YDHL", "图片插入时间大于1秒，不是截屏");
            return;
        }

        // 设置最大等待时间为500ms，手机保存截图有延迟
        while (!checkFilenameContainScreenShot(path) && duration <= 500) {
            try {
                duration += step;
                Thread.sleep(step);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (checkFilenameContainScreenShot(path)) {
            Message msg = Message.obtain();
            msg.what =  AppConfig.MAIN_SCREEN_SHOT;
            msg.obj = path;
            mHandler.sendMessage(msg);
        }
    }
    //判断文件名是否包含screen
    private boolean checkFilenameContainScreenShot(String path) {
        String[] KEYWORDS = {
                "screenshot", "screen_shot", "screen-shot", "screen shot", "screencapture",
                "screen_capture", "screen-capture", "screen capture", "screencap", "screen_cap",
                "screen-cap", "screen cap"
        };

        if (path == null) {
            return false;
        }
        path = path.toLowerCase();
        for (String keyword : KEYWORDS) {
            if (path.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}

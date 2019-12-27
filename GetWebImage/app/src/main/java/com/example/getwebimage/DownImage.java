package com.example.getwebimage;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import java.net.URL;

/**
 * 用来获取网络图片
 * @author Administrator
 *
 */
public class DownImage {
    public String image_path;

    public DownImage(String image_path) {
        this.image_path = image_path;
    }

    public void loadImage(final ImageCallBack callBack) {

        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Drawable drawable = (Drawable) msg.obj;
                callBack.getDrawable(drawable);
            }

        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Drawable drawable = Drawable.createFromStream(new URL(
                            image_path).openStream(), image_path);
                    Message message = Message.obtain();
                    message.obj = drawable;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface ImageCallBack {
        public void getDrawable(Drawable drawable);
    }
}

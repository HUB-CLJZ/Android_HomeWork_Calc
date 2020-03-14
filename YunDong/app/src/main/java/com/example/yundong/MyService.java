package com.example.yundong;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private MyBinder mBinder = new MyBinder();//实例化接口
    private IMyAidlForService.Stub mRemoteBinder = new IMyAidlForService.Stub() {
        @Override
        public void requsetRemoteCmd_2(int param1, long param2) throws RemoteException {
            //在此加入请求服务代码
        }
    };
    public MyService() {
    }
    public int I_requestCmd_2(){//通过公开方法方式，为客户端提供接口，调用服务
        return 1;
    }
    private MediaPlayer mMediaPlayer;
    public class MyBinder extends Binder{
        public MyService getService(){//返回服务上下文的接口方法
            return MyService.this;
        }
        public void music_play(){//播放
            //含播放器创建、准备代码
            //源1：使用应用资源，无需调用准备方法
            if(null==mMediaPlayer) {
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music01);
            }
            mMediaPlayer.start();
        }
        public void music_pause(){//暂停
            if(null!=mMediaPlayer)
                mMediaPlayer.pause();
        }
        public void music_stop(){//停止
            //含释放资源代码
            if(null!=mMediaPlayer) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;//返回接口类对象实例
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("YDHL:","MyService: onCreate() is called");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("YDHL:","MyService: onStartCommand() is called");
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {//服务销毁时，释放播放器资源
        if(null!=mMediaPlayer) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        super.onDestroy();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("YDHL:","MyService: onUnbind() is called");
        return super.onUnbind(intent);
    }
    @Override
    public void onRebind(Intent intent) {
        Log.d("YDHL:","MyService: onRebind() is called");
        super.onRebind(intent);
    }
}


package com.example.yundong;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.List;
import java.util.Map;

/*
定义为外部类形式，没有用内部类，避免主窗口代码太庞大导致维护不方便
 */
public class WorkThreadHandler extends Handler {
    private Handler mUIHandler;//保存主窗口handler引用

    public WorkThreadHandler(Handler uiHandler, Looper looper) {
        super(looper);
        mUIHandler = uiHandler;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {      //判断命令标志位
            case AppConfig.WORKTHREAD_UPDATE_TIANQI:
                //从队列移除多余的本ID的消息，本次更新后，过一段时间再更新
                this.removeMessages(AppConfig.WORKTHREAD_UPDATE_TIANQI);
                //从网络请求天气信息
                Message uimsg = Message.obtain();
                uimsg.what = AppConfig.MAIN_UPDATE_TIANQI;
                uimsg.obj = getTianqiFromNet();
                mUIHandler.sendMessage(uimsg);
                //延迟5分钟发送本ID的消息，模拟定时器5分钟后再更新。定时精度不高，可以用这种方法
                this.sendEmptyMessageDelayed(AppConfig.WORKTHREAD_UPDATE_TIANQI,1000*300);
                break;
            default:
                //做缺省工作
                break;
        }
    }
    //从网上请求天气信息
    private List<Map<String,String>> getTianqiFromNet(){
        return null;
    }
}

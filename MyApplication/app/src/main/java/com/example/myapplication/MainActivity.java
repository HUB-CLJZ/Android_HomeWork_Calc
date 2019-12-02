package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    /**
     *  m_handler:当前线程的handler
     *  m_trd1：线程一
     *  m_trd2: 线程二
     */
    private Handler m_handler;
    private ThreadSon m_trd1,m_trd2;

    private Button b1,b2,b3,b4;
    private TextView t1;
    static public String [] trdname ={"trd1","trd2"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        t1 = findViewById(R.id.text1);

        m_handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1001:
                        //收到子线程发的消息后打印
                        Log.d("WXSTC","CMD-1001");
                        t1.setText(t1.getText()+" cmd 1001");
                        break;
                    case 1002:
                        //收到子线程发的消息后打印
                        Log.d("WXSTC","CMD-1002" );
                        t1.setText(t1.getText()+" cmd 1002");
                        break;
                }
            }
        };
        //实例化子线程
        m_trd1 = new ThreadSon();
        m_trd2 = new ThreadSon();
        //设置子线程的名字
        m_trd1.setM_name(trdname[0]);
        m_trd2.setM_name(trdname[1]);
        //设置子线程所属的父线程
        m_trd1.setM_mainHandler(m_handler);
        m_trd2.setM_mainHandler(m_handler);
        //启动子线程
        m_trd1.start();
        m_trd2.start();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( m_trd1.getM_handler() != null) {
                    Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = 1000;
                    //给子线程发消息
                    m_trd1.getM_handler().sendMessage(msg);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( m_trd2.getM_handler() != null){
                    Message msg = new Message();
                    msg.what = 2;
                    msg.arg1 = 2000;
                    //给子线程发消息
                    m_trd2.getM_handler().sendMessage(msg);
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_trd1.getM_handler()!=null){
                    Message msg = new Message();
                    msg.what = 3;
                    msg.arg1 = 3000;
                    //给子线程发消息
                    m_trd1.getM_handler().sendMessage(msg);
                }
                if(m_trd2.getM_handler()!=null){
                    Message msg = new Message();
                    msg.what = 3;
                    msg.arg1 = 4000;
                    //给子线程发消息
                    m_trd2.getM_handler().sendMessage(msg);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m_trd1.showInfo();
                m_trd2.showInfo();

                if(m_trd1.getM_handler() != null){
                    Message msg = new Message();
                    msg.what = 4;
                    msg.arg1 = 666000;
                    //给子线程发消息
                    m_trd1.getM_handler().sendMessage(msg);
                }
                if(m_trd2.getM_handler()!=null){
                    Message msg = new Message();
                    msg.what = 4;
                    msg.arg1 = 666000;
                    m_trd2.getM_handler().sendMessage(msg);
                }
            }
        });

    }
}
class ThreadSon extends Thread {
    /***
     * m_name:区别不同线程而设置的线程名
     * m_handler:当前线程的handler
     * m_mainHandler:主线程的handler
     * */
    private String m_name;
    private Handler m_handler = null;
    private Handler m_mainHandler = null;

    public void showInfo() {
        Log.d("WXSTC","threadson object:"+m_name+" show info" );
    }

    public  void setM_name(String _name) {
        m_name = _name;
    }

    public void setM_mainHandler(Handler _mainHandler) {
        this.m_mainHandler = _mainHandler;
    }

    public Handler getM_handler() {
        return m_handler;
    }

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        m_handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg){
                switch (msg.what) {
                    case 1:
                        //收到主线程发的消息
                        Log.d("WXSTC","CMD-1");
                        break;
                    case 2:
                        //收到主线程发的消息
                        Log.d("WXSTC","CMD-2:"+msg.arg1 );
                        break;
                    case 3:
                        //收到主线程发的消息
                        Log.d("WXSTC","CMD-3:" );
                        if(m_name.equalsIgnoreCase(MainActivity.trdname[0])) {
                            Message msg1 = new Message();
                            msg1.what = 1001;
                            //发送给主线程
                            m_mainHandler.sendMessage(msg1);
                        }
                        if(m_name.equalsIgnoreCase(MainActivity.trdname[1])) {
                            Message msg1 = new Message();
                            msg1.what = 1002;
                            //发送给主线程
                            m_mainHandler.sendMessage(msg1);
                        }
                        break;
                    case 4://exit thread
                        this.getLooper().quit();
                        break;
                }
            }
        };
        Looper.loop();
        Log.d("WXSTC","CMD-4: thread exit" );
        m_handler = null;
        // m_name = null;
    }
}
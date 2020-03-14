package com.example.progressbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int mProgress = 0;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar = findViewById(R.id.progreebar);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x111) {
                    progressBar.setProgress(mProgress);
                } else {
                    Toast.makeText(MainActivity.this, "耗时操作完成",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mProgress = doWork();
                    Message m = new Message();
                    if (mProgress<100) {
                        m.what = 0x111;//自定义的消息代码,表示消息未完成
                        mHandler.sendMessage(m);
                    } else {
                        m.what = 0x110;//自定义消息代码，表示消息完成
                        mHandler.sendMessage(m);
                        break;
                    }
                }
            }

            private  int doWork() {
                mProgress += Math.random()*10;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return  mProgress;
            }
        }).start();

    }
}

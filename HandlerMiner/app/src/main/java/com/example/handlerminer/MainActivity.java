package com.example.handlerminer;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static TextView textView;
    public static Button button;
    public static Thread uiThread;
    public static Handler uiHandler;
    public static int parentWhat = 0x001;
    public static int childWhat = 0x002;
    private static Handler parentHandler = null;
    private static MinerThread minerThread = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_Hello);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        parentHandler = new ParentHandler();
        minerThread = new MinerThread();
//      = new MinerThread();
    }

    private static void handTest() {
        minerThread.setChildHandler(minerThread.getChildHandler());
        uiThread = new Thread(new Runnable() {
            String strParent = "你好";
            String strChild = "Hello";
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = parentWhat;
                message.obj = strParent;
                parentHandler.sendMessage(message);
                Looper.prepare();
                uiHandler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        Message mesChild = new Message();
                        mesChild.what = childWhat;
                        mesChild.obj = strChild;
                        parentHandler.sendMessage(mesChild);
                    }
                };
                Looper.loop();
            }
        });
        uiThread.start();
    }

    @Override
    public void onClick(View view) {
        handTest();
        minerThread.start();
    }
}

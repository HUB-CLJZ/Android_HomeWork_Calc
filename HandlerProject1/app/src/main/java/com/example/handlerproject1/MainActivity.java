package com.example.handlerproject1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
  * @Package:        com.example.handlerproject1
  * @ClassName:      MainActivity
  * @Description:    Java类作用描述 
  * @Author:         CLJZ
  * @CreateDate:     2019/11/7 17:09
  * @UpdateUser:     CLJZ
  * @UpdateDate:     2019/11/7 17:09
  * @UpdateRemark:   更新说明：
  * @Version:        1.0
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mian_btn_big;
    private TextView main_time_tvbig;
    private TextView title;
    private EditText main_et_time;
    private int time = 10;
    /**
     * @description:创建Handler处理计时结果和计时信息
     * @author: CLJZ
     * @date: 2019/11/7  18:32
     */
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.obj != null) {
                String titleName = (String)msg.obj;
                title.setText(titleName);
            } else {
                main_time_tvbig.setText(msg.what + "s");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindID();
    }
    /**
     * @description:对所以控件进行初始化
     * @author: CLJZ
     * @date: 2019/11/7  18:12
     * @param: []
     * @return: void
     */
    private void bindID() {
        main_et_time = findViewById(R.id.lay2_time);
        main_time_tvbig = findViewById(R.id.big_time);
        mian_btn_big = findViewById(R.id.btn_big);
        title = findViewById(R.id.titles);
        mian_btn_big.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_big) {
            //获取输入的计时信息
            time = Integer.parseInt(main_et_time.getText().toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (time >= 0) {
                        try {
                            //初始化计时器,识别信息
                            handler.sendEmptyMessage(time);
                            //每隔一秒显示一次
                            Thread.sleep(1000);
                            time--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //获得当前的信息
                    Message msg = handler.obtainMessage();
                    //设置信息为计时完成
                    msg.obj = "计时完成";
                    handler.sendMessage(msg);
                }
            }).start();
        }
    }
}
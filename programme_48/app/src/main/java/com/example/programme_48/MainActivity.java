package com.example.programme_48;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnCheckInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mBtnCheckInternet = findViewById(R.id.btn_check_internet);
        mBtnCheckInternet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check_internet:
                if (isNetworkConnected(this)) {

                    AlertDialog.Builder bulider = new AlertDialog.Builder(MainActivity.this);
                    //外部区域不允许点击
                    bulider.setCancelable(false);

                    View view = View.inflate(this,R.layout.dialog_layout,null);
                    bulider.setView(view);

                    TextView content = view.findViewById(R.id.tv_show);
                    content.setText("网络已连接");
                    TextView confirm = view.findViewById(R.id.tv_confirm);

                    final AlertDialog dialog = bulider.show();
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                } else {
                    AlertDialog.Builder bulider = new AlertDialog.Builder(MainActivity.this);
                    //外部区域不允许点击
                    bulider.setCancelable(false);

                    View view = View.inflate(this,R.layout.dialog_layout,null);
                    bulider.setView(view);

                    TextView content = view.findViewById(R.id.tv_show);
                    content.setText("网络已断开");
                    TextView confirm = view.findViewById(R.id.tv_confirm);

                    final AlertDialog dialog = bulider.show();
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
                break;
                default:
                    break;
        }
    }

    // 判断网络状态
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}

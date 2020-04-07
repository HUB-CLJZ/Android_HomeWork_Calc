package com.example.programme_36;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InfoActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private ImageView iv_icon;
    private TextView tv_introduce;
    private RatingBar rating_bar;
    private TextView tv_phone;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();


        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Intent intent = getIntent();
        String introduce = intent.getStringExtra("introduce");
        Integer score = intent.getIntExtra("score", 0);
        final Integer phone = intent.getIntExtra("phone", 0);
        Integer icon = intent.getIntExtra("icon", 0);

        Log.d("CLJZ", "接收到: " + introduce + "---" + score + "---" + phone + "---" + icon);

        iv_icon.setImageResource(icon);
        tv_introduce.setText(tv_introduce.getText() + introduce);
        tv_phone.setText(tv_phone.getText() + phone.toString());
        rating_bar.setRating(score);

        //权限的申请
        int callPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
        if(callPermission != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},0x0005);
        }

        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:"+phone);
                callIntent.setData(uri);
                startActivity(callIntent);
            }
        });


    }

    private void initView() {
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_introduce = (TextView) findViewById(R.id.tv_introduce);
        rating_bar = (RatingBar) findViewById(R.id.rating_bar);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
    }

    /***
     * 权限请求结果的处理
     * @param requestCode 请求的特征码
     * @param permissions
     * @param grantResults 请求的结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0x0005) {
            if (grantResults.length==1 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED) {
                Log.d("CLJZ", "onRequestPermissionsResult: 有权限");
            } else {
                Log.d("CLJZ", "onRequestPermissionsResult: 有权限");
            }
        }
    }
}

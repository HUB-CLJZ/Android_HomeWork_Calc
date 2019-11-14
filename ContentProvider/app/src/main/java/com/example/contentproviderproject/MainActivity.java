package com.example.contentproviderproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private TextView tvSms;
    private TextView tvDes;
    private String text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSms = findViewById(R.id.tv_sms);
        tvDes = findViewById(R.id.tv_des);
    }

    public void readSMS(View view) {
        Uri uri = Uri.parse("content://sms/");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri,new String[]{"_id","address","type","body","date"},null,null,null);
        List<SmsInfo> smsInfos = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            tvDes.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(0);
                String address = cursor.getString(1);
                int type = cursor.getInt(2);
                String body = cursor.getString(3);
                long date = cursor.getLong(4);
                SmsInfo smsInfo = new SmsInfo(_id,address,type,body, date);
                smsInfos.add(smsInfo);
            }
            cursor.close();
        }

        //将查询到的短信内容显示到界面上
        for (int i = 0; i < smsInfos.size(); i++) {
            text += "手机号码:" + smsInfos.get(i).getAdress()+"\n";
            text += "短信内容:" + smsInfos.get(i).getBody() + "\n\n";
            tvSms.setText(text);
        }
    }
}

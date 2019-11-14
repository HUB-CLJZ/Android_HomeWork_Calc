package com.example.actionanddata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button call;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call = findViewById(R.id.call);
        send = findViewById(R.id.send);

        call.setOnClickListener(this);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.call:
                intent.setAction(intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:17763195054"));
                startActivity(intent);
                break;

            case R.id.send:
                intent.setAction(intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:5554"));
                intent.putExtra("sms_body","你好，世界");
                startActivity(intent);
                break;
        }
    }
}

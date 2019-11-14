package com.example.broadcastreceiverdemo;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.Bordercast);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送一条广播
                Intent intent = new Intent();
                intent.setPackage(getPackageName());
                intent.setAction("zzzz");
                sendBroadcast(intent);
            }
        });

    }
}

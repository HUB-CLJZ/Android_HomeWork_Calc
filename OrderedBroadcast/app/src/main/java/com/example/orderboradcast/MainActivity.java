package com.example.orderboradcast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view) {
        Intent intent = new Intent();
        intent.setAction("Intercept_Stitch");
        sendOrderedBroadcast(intent,null);

        //必须接受的广播
        MyReceiver2 receiver2 = new MyReceiver2();
        sendOrderedBroadcast(intent,null,receiver2,null,0,null,null);
    }
}

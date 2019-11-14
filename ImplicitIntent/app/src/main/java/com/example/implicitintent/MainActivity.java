package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        button = findViewById(R.id.open_web);
    }

    public void click(View view) {
        Intent intent = new Intent();
        intent.setAction(intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.zhihu.com"));
        startActivity(intent);
    }


}

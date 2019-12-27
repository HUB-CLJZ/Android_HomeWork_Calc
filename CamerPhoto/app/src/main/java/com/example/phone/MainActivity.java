package com.example.phone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView photoResultContainer;
    private ImageView btn;
    private static final int RESULT_CODE = 1;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.media.action.IMAGE_CAPTURE");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(intent,RESULT_CODE);
            }
        });
    }

    private void initView() {
        photoResultContainer = findViewById(R.id.photo_result_container);
        btn = findViewById(R.id.btn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE) {
            Log.d(TAG, "onActivityResult-data: "+data);
            Log.d(TAG, "onActivityResult-requestCode: "+Activity.RESULT_OK);
            if (resultCode == Activity.RESULT_OK) {
                Bitmap result = (Bitmap) data.getExtras().get("data");
                Log.d(TAG, "onActivityResult-bitmap: "+result);
                if (result != null) {
                    photoResultContainer.setImageBitmap(result);
                }
            } else if (requestCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "您取消了拍照", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

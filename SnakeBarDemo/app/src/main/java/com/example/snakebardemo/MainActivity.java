package com.example.snakebardemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * 参数1，veiw对象
     * 参数2，snake提示的文字
     * setAction，设置点击事件(1，点击的文字，2.点击的事件)
     * setActionTextColor，点击文字的颜色
     *
     *
     */
    public void startSnake(View view) {
        Snackbar.make(view,"我是snakebar",Snackbar.LENGTH_INDEFINITE).setAction("点一下吧",new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你点击率snakebar", Toast.LENGTH_SHORT).show();
            }
        }).setActionTextColor(Color.RED).show();
    }
}

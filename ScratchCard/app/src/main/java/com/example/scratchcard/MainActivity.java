package com.example.scratchcard;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap alterBitmap;
    private double nX, nY;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imgV);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scratch_card);
        alterBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        nX = (double)bitmap.getWidth() / dm.widthPixels;
        nY = (double)bitmap.getHeight() / dm.heightPixels;

        //创建画布
        Canvas canvas = new Canvas(alterBitmap);
        //创建画笔设置颜色
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        //设置平滑显示（抗锯齿）
        paint.setAntiAlias(true);

        //创建Matrix对象，为图片添加效果
        Matrix matrix = new Matrix();
        canvas.drawBitmap(bitmap,matrix,paint);

        //为ImageView设置触摸监听
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    int x = (int)motionEvent.getX();
                    int y = (int)motionEvent.getY();

                    for (int i = -100; i < 100; i++) {
                        for (int j = -100; i < 100; j++) {
                            if ((Math.sqrt((i*i) + (j*j)) <= 100)) {
                                alterBitmap.setPixel((int)(x*nX) + i,(int)(y*nY+90) + j,Color.TRANSPARENT);
                            }
                        }
                    }
                    imageView.setImageBitmap(alterBitmap);
                } catch (Exception e) {
                    //捕获异常，防止用户触摸图片以外的地方而异常退出
                    e.printStackTrace();
                }
                return true;
            }
        });


    }
}

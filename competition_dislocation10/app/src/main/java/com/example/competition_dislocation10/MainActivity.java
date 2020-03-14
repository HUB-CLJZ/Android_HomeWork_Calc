package com.example.competition_dislocation10;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
/**
 * @author admin
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private ImageView imageView;
    private ConstraintLayout parent_view;
    //起点坐标
    private PointF start;
    //终点坐标
    private PointF end;
    //移动矩阵
    private Matrix matrix = new Matrix();
    //保存矩阵，防止偏移起点错误。原因未知
    private Matrix sava_matirx = new Matrix();
    //拖拽状态的变量
    private static final int NONE = 0;//没有按下
    private static final int DRAG = 1;//拖动
    private static final int ZOOM = 2;//缩放
    private int mode = NONE;
    //缩放的中点
    private PointF mid = new PointF();
    //老的缩放的
    private float oldDist = 1f;

    private float width = 0F;
    private float height = 0F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.photo);
        //图片设置触摸监听
        imageView.setOnTouchListener(this);
        //获取屏幕的宽度，高度,用来设置缩放中心（缩放中心也可以为两手指中间点），需要调用midPoint方法计算
        width = getResources().getDisplayMetrics().widthPixels;
        height = getResources().getDisplayMetrics().heightPixels;
        Log.d("MainActivity", "width: "+width+"----height"+height);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //向下转型为ImageView
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            //单点，第一次按下
            case MotionEvent.ACTION_DOWN:
                Log.d("MainActivity", "ACTION_DOWN: "+event.getX()+"---"+event.getY());
                //保存起始位置，防止移动过程中，发生平移起点变化
                sava_matirx.set(matrix);
                start = new PointF(event.getX(),event.getY());
                //初始化为拖拽
                mode = DRAG;
                break;
            //单点，移动的过程
            case MotionEvent.ACTION_MOVE:
                Log.d("MainActivity", "ACTION_MOVE: "+event.getX()+"---"+event.getY());
                //当状态是拖拽时，进行拖拽
                if (mode == DRAG) {
                    //取出保存的矩阵
                    matrix.set(sava_matirx);
                    end = new PointF(event.getX(),event.getY());
                    //偏移量的计算
                    float dx = end.x - start.x;
                    float dy = end.y - start.y;
                    //矩阵进行平移变换
                    matrix.postTranslate(dx,dy);
                    imageView.setImageMatrix(matrix);
                    //当状态时缩放是，进行缩放
                } else if (mode == ZOOM) {
                    //第二次触摸时，缩放的距离
                    float newDist = spacing(event);
                    //去除无效缩放值
                    if (newDist > 10F) {
                        matrix.set(sava_matirx);
                        //计算缩放比例
                        float scale = (newDist/oldDist);
                        //mid.x,mid.y为缩放的中点
                        matrix.postScale(scale,scale,width/2,height/2);
                        imageView.setImageMatrix(matrix);
                    }
                }
                break;
            //单点，松开
            case MotionEvent.ACTION_UP:
                break;
            //多点触摸按下
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10F) {
                    sava_matirx.set (matrix);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
        }
        return true;
    }

    //计算两点之间的距离
    private float spacing (MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x*x+y*y);
    }

    //计算点的中点
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x/2,y/2);
    }
}
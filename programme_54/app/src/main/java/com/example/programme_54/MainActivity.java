package com.example.programme_54;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_collection;
    private Button btn_delete;
    private LinearLayout ll_line;
    private float moveX;
    private float moveY;
    private float pressX;
    private float pressY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        addTouchListener();
    }

    private void addTouchListener() {
        ll_line.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //按下
                    case MotionEvent.ACTION_DOWN:
                        pressX = event.getX();
                        pressY = event.getY();
                        break;
                    //移动
                    case MotionEvent.ACTION_MOVE:
                        moveX = event.getX();
                        moveY = event.getY();
                        break;
                    //松开
                    case MotionEvent.ACTION_UP:
                        if (moveX-pressX > 0 && Math.abs(moveY - pressY) < 200) {
                            //向右滑动
                            btn_collection.setVisibility(View.INVISIBLE);
                            btn_delete.setVisibility(View.INVISIBLE);
                        } else if (moveX - pressX < 0 && Math.abs(moveY - pressY) < 200) {
                            //向左滑动
                            btn_collection.setVisibility(View.VISIBLE);
                            btn_delete.setVisibility(View.VISIBLE);
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        btn_collection = (Button) findViewById(R.id.btn_collection);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        ll_line = (LinearLayout) findViewById(R.id.ll_line);
        btn_collection.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_line:

                break;
            case R.id.btn_collection:

                break;
            case R.id.btn_delete:

                break;

            default:
                    break;
        }
    }


}

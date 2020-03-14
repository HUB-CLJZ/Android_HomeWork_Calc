package com.example.yundong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class HeadimageActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView[] mImageViewArray = new ImageView[6];
    //6个图片地址
    String[] imgUrls = { "https://www.baidu.com/img/pc_1c6e30772d5e4103103bd460913332f9.png",
            "https://www.baidu.com/img/pc_1c6e30772d5e4103103bd460913332f9.png",
            "https://www.baidu.com/img/pc_1c6e30772d5e4103103bd460913332f9.png",
            "https://www.baidu.com/img/PCpad_bc531b595cf1e37c3907d14b69e3a2dd.png",
            "https://www.baidu.com/img/PCpad_bc531b595cf1e37c3907d14b69e3a2dd.png",
            "https://www.baidu.com/img/PCpad_bc531b595cf1e37c3907d14b69e3a2dd.png"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headimge);

        findViewById(R.id.imageViewheadpick_1).setOnClickListener(this);
        findViewById(R.id.imageViewheadpick_2).setOnClickListener(this);
        findViewById(R.id.imageViewheadpick_3).setOnClickListener(this);
        findViewById(R.id.imageViewheadpick_4).setOnClickListener(this);
        findViewById(R.id.imageViewheadpick_5).setOnClickListener(this);
        findViewById(R.id.imageViewheadpick_6).setOnClickListener(this);
        findViewById(R.id.imageViewheadpick_7).setOnClickListener(this);
        findViewById(R.id.imageViewheadpick_8).setOnClickListener(this);
        findViewById(R.id.imageViewheadpick_9).setOnClickListener(this);
        findViewById(R.id.button_selectimgfromfile).setOnClickListener(this);
        mImageViewArray[0] = findViewById(R.id.imageViewheadpick_net_1);
        mImageViewArray[1] = findViewById(R.id.imageViewheadpick_net_2);
        mImageViewArray[2] = findViewById(R.id.imageViewheadpick_net_3);
        mImageViewArray[3] = findViewById(R.id.imageViewheadpick_net_4);
        mImageViewArray[4] = findViewById(R.id.imageViewheadpick_net_5);
        mImageViewArray[5] = findViewById(R.id.imageViewheadpick_net_6);
        for(int i=0;i<6;i++)
            mImageViewArray[i].setOnClickListener(this);

        //使用Volley加载网络图片
        getImageFromNetByVolley();

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        int resultCode = 102;
        int index = -1;
        String netImageFile = null;
        switch (view.getId()){
            case R.id.imageViewheadpick_1:
                intent.putExtra("imagesel",R.drawable.headpic_01);
                break;
            case R.id.imageViewheadpick_2:
                intent.putExtra("imagesel",R.drawable.headpic_02);
                break;
            case R.id.imageViewheadpick_3:
                intent.putExtra("imagesel",R.drawable.headpic_03);
                break;
            case R.id.imageViewheadpick_4:
                intent.putExtra("imagesel",R.drawable.headpic_04);
                break;
            case R.id.imageViewheadpick_5:
                intent.putExtra("imagesel",R.drawable.headpic_05);
                break;
            case R.id.imageViewheadpick_6:
                intent.putExtra("imagesel",R.drawable.headpic_06);
                break;
            case R.id.imageViewheadpick_7:
                intent.putExtra("imagesel",R.drawable.headpic_07);
                break;
            case R.id.imageViewheadpick_8:
                intent.putExtra("imagesel",R.drawable.headpic_08);
                break;
            case R.id.imageViewheadpick_9:
                intent.putExtra("imagesel",R.drawable.headpic_09);
                break;
            case R.id.imageViewheadpick_net_1:
                resultCode = 103;
                index = 0;
                netImageFile = "netpic01.png";
                break;
            case R.id.imageViewheadpick_net_2:
                resultCode = 103;
                index = 1;
                netImageFile = "netpic02.png";
                break;
            case R.id.imageViewheadpick_net_3:
                resultCode = 103;
                index = 2;
                netImageFile = "netpic03.png";
                break;
            case R.id.imageViewheadpick_net_4:
                resultCode = 103;
                index = 3;
                netImageFile = "netpic04.png";
                break;
            case R.id.imageViewheadpick_net_5:
                resultCode = 103;
                index = 4;
                netImageFile = "netpic05.png";
                break;
            case R.id.imageViewheadpick_net_6:
                resultCode = 103;
                index = 5;
                netImageFile = "netpic06.png";
                break;
            case R.id.button_selectimgfromfile:

                break;
            default:
                intent.putExtra("imagesel", R.drawable.headpic_default);
                break;
        }
        if(103==resultCode){
            //保存图片
            AppConfig.saveImage(this,netImageFile,
                    ((BitmapDrawable)mImageViewArray[index].getDrawable()).getBitmap());
            intent.putExtra("imagesel",netImageFile);
        }

        setResult(resultCode,intent);
        finish();
    }
    //使用Volley网络的方法，在onCreate()方法中调用
    private void getImageFromNetByVolley(){

        //步骤：初始化6个监听器
        MyResponseListenerAndErrorListener[] mListener = new MyResponseListenerAndErrorListener[6];
        for(int i=0;i<6;i++){
            mListener[i] = new MyResponseListenerAndErrorListener(i);
        }

        //步骤：创建请求队列
        RequestQueue myQueue = Volley.newRequestQueue(this);

        //步骤：创建6个图片请求对象，并加入队列
        ImageRequest[] imageRequests= new ImageRequest[6];
        for(int i=0;i<6;i++){
            imageRequests[i] = new ImageRequest(imgUrls[i],mListener[i],
                    0,0,Bitmap.Config.RGB_565,mListener[i]);

            //加入请求队列
            myQueue.add(imageRequests[i]);
        }

    }
    //以内部类的形式定义了Response的监听器，方便使用
    class MyResponseListenerAndErrorListener implements Response.Listener<Bitmap>,Response.ErrorListener{
        private int mWhich;//ImageView数组索引
        public MyResponseListenerAndErrorListener(int which){
            mWhich = which;
        }
        @Override
        public void onResponse(Bitmap response) {
            mImageViewArray[mWhich].setImageBitmap(response);//更新界面组件图片

        }

        @Override
        public void onErrorResponse(VolleyError error) {
            //更新界面组件为缺省图片
            mImageViewArray[mWhich].setImageResource(android.R.drawable.btn_dialog);
        }
    }
}

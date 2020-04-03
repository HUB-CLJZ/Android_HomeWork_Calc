package com.example.newssubscription;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout[] mLineding,mLinefa;
    private FrameLayout[] mFrameltDinglist,mFrameltFalist;
    private final int COLUMNS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //订阅列表和发送列表都占两行
        mLineding= new LinearLayout[2];
        mLinefa = new LinearLayout[2];

        //设置订阅列表最多放五个
        mLineding[0] = findViewById(R.id.linearlayout_dingyue);
        mLineding[1] = findViewById(R.id.linearlayout_dingyue2);
        mLineding[0].setWeightSum(5);
        mLineding[1].setWeightSum(5);

        //设置添加列表最多放五个
        mLinefa[0] = findViewById(R.id.linearlayout_daitianjia);
        mLinefa[1] = findViewById(R.id.linearlayout_daitianjia2);
        mLinefa[0].setWeightSum(5);
        mLinefa[1].setWeightSum(5);

        //订阅列表
        mFrameltDinglist = new FrameLayout[8];

        //发布列表
        mFrameltFalist = new FrameLayout[8];

        //初始化
        for(int i=0; i < 8; i++) {

            //每一项添加进fragment
            FrameLayout frameLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.publish_item, null);

            //选择框的监听事件
            CheckBox checkBox = frameLayout.findViewById(R.id.checkBox123);
            checkBox.setOnClickListener(this);

            //tag中记录原始顺序
            checkBox.setTag(i);
            checkBox.setChecked(true);

            //文本的监听事件
            TextView tx = frameLayout.findViewById(R.id.textView_one);
            tx.setText("文本"+i);

            //设置线性布局的参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);
            frameLayout.setLayoutParams(params);

            //添加进发布列表
            mFrameltFalist[i]= frameLayout;

            if(i < COLUMNS) {
                mLinefa[0].addView(frameLayout);
            } else {
                mLinefa[1].addView(frameLayout);
            }
        }
    }

    @Override
    public void onClick(View v) {

        CheckBox box = (CheckBox)v;
        int  index = Integer.parseInt( box.getTag().toString() );

        //在发布列表里，要加到订阅列表
        if(!box.isChecked()) {
            mFrameltDinglist[index] = mFrameltFalist[index];
            mFrameltFalist[index] = null;
        } else {
            //移动到发布列表
            mFrameltFalist[index] = mFrameltDinglist[index];
            mFrameltDinglist[index] = null;
        }

        //清空发布线性布局
        for(int j=0; j<2; j++) {
            mLineding[j].removeAllViews();
            mLinefa[j].removeAllViews();
        }

        //重新装入子VIEW
        int countding=0, countfa = 0;

        for(int j=0; j < mFrameltFalist.length; j++) {

            if( mFrameltFalist[j] != null) {
                countfa++;
                if (countfa <= COLUMNS) {
                    mLinefa[0].addView(mFrameltFalist[j]);
                } else {
                    mLinefa[1].addView(mFrameltFalist[j]);
                }
            }

            if(mFrameltDinglist[j] != null) {
                countding++;
                if (countding <= COLUMNS) {
                    mLineding[0].addView(mFrameltDinglist[j]);
                }
                else {
                    mLineding[1].addView(mFrameltDinglist[j]);
                }
            }
        }
    }
}
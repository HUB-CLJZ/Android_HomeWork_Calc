package com.example.ratingbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
/***
 *
 * 参考文献：https://blog.csdn.net/lzfengluo/article/details/86737709
 *
 * */
public class MainActivity extends AppCompatActivity {

    private RatingBar mRatingBar;
    private String TAG = "CLJZ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRatingBar = findViewById(R.id.ratingbar);

        //监听进度改变
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //选中几颗星
                Log.d(TAG,  "您选中了"+ mRatingBar.getRating()+"颗星星");

                //共有多少颗星
                Log.d(TAG,  "共有"+ mRatingBar.getNumStars()+"颗星星");

                //获取进度，选了多少次
                Log.d(TAG,  "进度为"+ mRatingBar.getProgress());

                //获取步数
                Log.d(TAG,  "步数为"+ mRatingBar.getStepSize());
            }
        });


    }
}

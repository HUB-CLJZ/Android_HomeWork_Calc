package com.example.frameanimation;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
/**
  * @Package:        com.example.frameanimation
  * @ClassName:      MainActivity
  * @Description:    演示逐帧动画的程序
  * @Author:         CLJZ
  * @CreateDate:     2019/11/9 15:44
  * @UpdateUser:     CLJZ
  * @UpdateDate:     2019/11/9 15:44
  * @UpdateRemark:   更新说明：
  * @Version:        1.0
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView flower;
    private Button play;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //playFrame();
    /*---------------------------------------------------------------------------------------------*/
        init();
    }

    /**
     * @description:通过代码播放动画
     * @author: CLJZ
     * @date: 2019/11/9  16:08
     * @param: []
     * @return: void
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void playFrame() {
        flower = findViewById(R.id.flower);
        AnimationDrawable animationDrawable = new AnimationDrawable();
        flower.setBackground(animationDrawable);
        //添加播放的图片,并设置间隔时间
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.people_01),50);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.people_02),50);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.people_03),50);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.people_04),50);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.people_05),50);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.people_06),50);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.people_07),50);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.people_08),50);
        //设置循环播放
        animationDrawable.setOneShot(false);
        //开始播放动画
        animationDrawable.start();
    }

    /**
     * @description:初始化方法，对所有控件进行初始化
     * @author: CLJZ
     * @date: 2019/11/9  16:07
     * @param: []
     * @return: void
     */
    private void init() {

        flower = findViewById(R.id.flower);
        play = findViewById(R.id.play);
        play.setOnClickListener(this);
        animationDrawable = (AnimationDrawable)flower.getBackground();
    }
    /**
     * @description:按钮的点击时间，动画的播放
     * @author: CLJZ
     * @date: 2019/11/9  15:53
     * @param: [view]
     * @return: void
     */
    @Override
    public void onClick(View view) {
        //如果动画没有播放，则开始播放动画，并且将按钮设置为播放状态,已经播放则相反
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
            play.setBackgroundResource(android.R.drawable.ic_media_pause);
        } else {
            animationDrawable.stop();
            play.setBackgroundResource(android.R.drawable.ic_media_play);
        }
    }
}

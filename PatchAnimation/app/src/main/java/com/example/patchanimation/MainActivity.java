package com.example.patchanimation;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static ImageView ivBeans;
    private static Button btnOne;
    private static Button btnTwo;
    private static Button btnThree;
    private static Button btnFour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过代码的方式播放动画
        //playAnimation();
/*-----------------------------------------------------------------------------------------------------*/
        //通过设置布局属性播放动画
        init();
    }
    /**
     * @description:对所有的控件进行初始化
     * @author: CLJZ
     * @date: 2019/11/9  14:18
     * @param: []
     * @return: void
     */
    private void init() {
        ivBeans = findViewById(R.id.ivBeans);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        ivBeans.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
    }
    /**
     * @description:为所有的按钮添加单击时间，对动画进行处理
     * @author: CLJZ
     * @date: 2019/11/9  14:18
     * @param: [view]
     * @return: void
     */
    @Override
    public void onClick(View view) {
        switch ( view.getId()){
            case R.id.btnOne:
                Animation alpha = AnimationUtils.loadAnimation(this,R.anim.alpha_animation);
                ivBeans.startAnimation(alpha);
                break;
            case R.id.btnTwo:
                Animation rotate = AnimationUtils.loadAnimation(this,R.anim.rotate_animation);
                ivBeans.startAnimation(rotate);
                break;
            case R.id.btnThree:
                Animation scale = AnimationUtils.loadAnimation(this,R.anim.scale_animation);
                ivBeans.startAnimation(scale);
                break;
            case R.id.btnFour:
                Animation translate = AnimationUtils.loadAnimation(this,R.anim.translate_animation);
                ivBeans.startAnimation(translate);
                break;
            default:
                break;
        }
    }
    public void playAnimation() {
        //通过Java代码实现动画
        ivBeans = findViewById(R.id.ivBeans);
        //创建一个渐变透明度变化的动画，透明度从透明到完全不透明
        AlphaAnimation animation = new AlphaAnimation(0.0f,1.0f);
        //设置播放时间为5秒
        animation.setDuration(5000);
        //设置重复方式为反向播放
        animation.setRepeatMode(AlphaAnimation.REVERSE);
        //设置重复次数为无限重复
        animation.setRepeatCount(AlphaAnimation.INFINITE);
        //播放动画
        ivBeans.startAnimation(animation);
    }

}

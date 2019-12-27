package com.example.competition_dislocation2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏注意这句一定要写在setContentView()方法的前面，不然会报错的
        //requestWindowFeature（Window.FEATURENO_TILE）；
        //取消状态栏
        //getWindow（）.setFlags（Windowlanager.LayoutParams.FLAG FULLSCREEN，Windowlanager.LayoutParams.FLAG FULLSCREEN）；
        //隐藏藏ActionBar
        //getSupportActionBar().hide();
        //去掉标题栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }
}
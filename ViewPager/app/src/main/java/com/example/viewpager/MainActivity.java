package com.example.viewpager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.graphics.Color;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    private ViewPager mLooperPager;
    private MyPagerAdapter myPagerAdapter;
    private  List<Integer> sColer = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //添加颜色，红黄蓝
        sColer.add(Color.RED);
        sColer.add(Color.BLUE);
        sColer.add(Color.YELLOW);
        //添加进适配器
        myPagerAdapter.setData(sColer);

        myPagerAdapter.notifyDataSetChanged();
    }

    private void initView() {
        //找到数据
        mLooperPager = findViewById(R.id.looper_pager);
        //设置适配器
        myPagerAdapter = new MyPagerAdapter();
        mLooperPager.setAdapter(myPagerAdapter);
    }
}

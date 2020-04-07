package com.example.programme_06;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.programme_06.adaper.FramentAdaper;
import com.example.programme_06.fragment.CO2Fragment;
import com.example.programme_06.fragment.HumidityFragment;
import com.example.programme_06.fragment.PM2_5Fragment;
import com.example.programme_06.fragment.TemperatureFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
//https://www.cnblogs.com/dongxiaodong/p/10211593.html
//https://blog.csdn.net/qq_18148011/article/details/52642057
public class MainActivity extends AppCompatActivity {
    private ViewPager mPager;
    private List<Fragment> mData = new ArrayList<>();
    private FramentAdaper framentAdaper;
    private TabLayout tabLayout;

    //选中图片
    private int[] select_img = {R.drawable.icon_background,
                                R.drawable.icon_background,
                                R.drawable.icon_background,
                                R.drawable.icon_background};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab_layout);

        //添加页面进ViewPager
        mData.add(new TemperatureFragment());
        mData.add(new HumidityFragment());
        mData.add(new PM2_5Fragment());
        mData.add(new CO2Fragment());
        framentAdaper = new FramentAdaper(getSupportFragmentManager(), mData);
        mPager.setAdapter(framentAdaper);

        //绑定
        tabLayout.setupWithViewPager(mPager);

        //设置默认选中页，宏定义
        tabLayout.getTabAt(0).select();

        //设置向左和向右都缓存的页面个数
        mPager.setOffscreenPageLimit(4);

        //初始化菜单栏显示
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //寻找到控件
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_item_layout, null);
            ImageView mTabIcon = view.findViewById(R.id.item_img);
            mTabIcon.setImageResource(select_img[i]);
            tabLayout.getTabAt(i).setCustomView(view);
        }
    }
}
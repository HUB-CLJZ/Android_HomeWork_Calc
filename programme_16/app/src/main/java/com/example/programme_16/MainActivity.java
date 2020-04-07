package com.example.programme_16;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/***
 * 充值记录，阀值设置与前面的题类似
 */
public class MainActivity extends AppCompatActivity {
    private TabLayout mTab_layout;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentData = new ArrayList<>();

    private FramentAdaper mFramentAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mFragmentData.add(new InfoFragment());
        mFragmentData.add(new RechargeFragment());
        mFragmentData.add(new ThresholdFragment());

        mFramentAdaper = new FramentAdaper(getSupportFragmentManager(),mFragmentData);

        mViewPager.setAdapter(mFramentAdaper);

        mTab_layout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mTab_layout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
    }
}

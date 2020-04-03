package com.example.competition_dislocation8;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager pager = findViewById(R.id.pager);
        TabLayout tab = findViewById(R.id.tab);
        //
        mData.add(new VideoFragment());
        mData.add(new PhotoFragment());
        MyAdaper myAdaper = new MyAdaper(getSupportFragmentManager(),mData);
        pager.setAdapter(myAdaper);
        //设置viewPager
//        tab.setupWithViewPager(pager);
    }
}

package com.example.programme_39;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab_layout;
    private ViewPager view_pager;
    private FragmentAdaper fragmentAdaper;

    private List<Fragment> mDate = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mDate.add(new OneFragment());
        mDate.add(new TwoFragment());
        mDate.add(new ThreeFragment());

        fragmentAdaper = new FragmentAdaper(getSupportFragmentManager(),mDate);

        view_pager.setAdapter(fragmentAdaper);

        tab_layout.setupWithViewPager(view_pager);



    }

    private void initView() {
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
    }
}

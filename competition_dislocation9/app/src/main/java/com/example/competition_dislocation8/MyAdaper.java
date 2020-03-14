package com.example.competition_dislocation8;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;
public class MyAdaper extends FragmentPagerAdapter {
    private List<Fragment> list;
    public MyAdaper(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "违章视频";
        } else {
            return "违章图片";
        }
    }
}

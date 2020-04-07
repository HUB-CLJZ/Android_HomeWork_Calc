package com.example.programme_39;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentAdaper extends FragmentPagerAdapter {
    private List<Fragment> mDate;
    private String[] mTitle = {"科技","教育","体育"};

    public FragmentAdaper(@NonNull FragmentManager fm,List<Fragment> mDate) {
        super(fm);
        this.mDate =mDate;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mDate.get(position);
    }

    @Override
    public int getCount() {
        return mDate.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}

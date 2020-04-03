package com.example.code_16.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.code_16.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_info extends Fragment {
    private Fragment  mFrgmtInfoS1, mFrgmtInfoS2;
    private List<Fragment> mFrgmtList;
    private ViewPager mVwPager;
    private TabLayout mTbly;
    private String[]  mTbTitle={"运动知识","运动场地"};

    public Fragment_info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fmroot = inflater.inflate(R.layout.fragment_fragment_info,container,false);

        mFrgmtInfoS1 =  new Fragment_info_s1();
        mFrgmtInfoS2 =  new Fragment_info_s2();
        mFrgmtList =  new ArrayList<>();
        mFrgmtList.add( mFrgmtInfoS1);
        mFrgmtList.add( mFrgmtInfoS2);

        mVwPager = fmroot.findViewById(R.id.frgmt_info_viewpage);
        mTbly = fmroot.findViewById(R.id.frgmt_info_tab);

        mVwPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFrgmtList.get(position);
            }

            @Override
            public int getCount() {
                return mFrgmtList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTbTitle[position];
            }

        });



        mTbly.setupWithViewPager(mVwPager);
        return fmroot;
    }

}

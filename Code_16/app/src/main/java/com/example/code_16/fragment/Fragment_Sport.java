package com.example.code_16.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.code_16.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Sport extends Fragment {
    private View  mVw1,  mVw2, mVw3;
    private List<View> mVwList;
    private ViewPager mVwPage;

    public Fragment_Sport() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_fragment__sport, container,  false);
        mVwPage = root.findViewById(R.id.viewPager_one );

        mVw1 = inflater.inflate(R.layout.layout_viewpage1,null);
        mVw2 = inflater.inflate(R.layout.layout_viewpage2,null);
        mVw3 = inflater.inflate(R.layout.layout_viewpage3,null);

        mVwList =  new ArrayList<>();
        mVwList.add(mVw1);
        mVwList.add(mVw2);
        mVwList.add(mVw3);

        mVwPage.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mVwList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(mVwList.get(position));
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(mVwList.get(position));
                return mVwList.get(position);
            }
        });
        return root;
    }

}

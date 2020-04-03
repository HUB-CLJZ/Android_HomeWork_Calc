package com.example.programme_04;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Fragment> mData = new ArrayList<>();
    private ViewPager mPager;
    private FragmentAdaper fragmentAdaper;
    private RadioButton mRbVideo;
    private RadioButton mRbPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mPager = findViewById(R.id.pager);

        mData.add(new VideoFragment());
        mData.add(new PhotoFragment());

        fragmentAdaper = new FragmentAdaper(getSupportFragmentManager(), mData);
        mPager.setAdapter(fragmentAdaper);

        mRbPhoto.setOnClickListener(this);
        mRbVideo.setOnClickListener(this);
    }


    private void initView() {
        mRbVideo = (RadioButton) findViewById(R.id.rb_video);
        mRbPhoto = (RadioButton) findViewById(R.id.rb_photo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_video:
                mPager.setCurrentItem(0);
                break;
            case R.id.rb_photo:
                mPager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }
}

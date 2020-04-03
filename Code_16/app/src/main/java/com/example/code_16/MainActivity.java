package com.example.code_16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.code_16.fragment.Fragment_Device;
import com.example.code_16.fragment.Fragment_My;
import com.example.code_16.fragment.Fragment_Sport;
import com.example.code_16.fragment.Fragment_info;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private Fragment mFrgmtSport, mFrgmtInfo, mFrgmtDevice, mFrgmtMy;
    private Fragment[]  mFrgmtArray;
    private int  mCurFrag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化底部导航栏
        mFrgmtDevice =  new Fragment_Device();
        mFrgmtInfo =  new Fragment_info();
        mFrgmtMy =  new Fragment_My();
        mFrgmtSport =  new Fragment_Sport();
        mFrgmtArray =  new Fragment[]{ mFrgmtSport, mFrgmtInfo, mFrgmtDevice, mFrgmtMy};

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fmlayout_main, mFrgmtSport).hide( mFrgmtSport)
                .add(R.id.fmlayout_main, mFrgmtInfo).hide( mFrgmtInfo)
                .add(R.id.fmlayout_main, mFrgmtDevice).hide( mFrgmtDevice)
                .add(R.id.fmlayout_main, mFrgmtMy).hide( mFrgmtMy).commit();

        mBottomNavigationView = findViewById(R.id.botmview_main);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int last =  mCurFrag;
                switch (menuItem.getItemId()) {
                    case R.id.navbomitem_sport:
                        mCurFrag = 0;
                        getSupportFragmentManager().beginTransaction()
                                .hide(mFrgmtArray[last]).show( mFrgmtArray[ mCurFrag])
                                .commit();
                        return true;
                    case R.id.navbomitem_info:
                        mCurFrag = 1;
                        getSupportFragmentManager().beginTransaction()
                                .hide( mFrgmtArray[last]).show( mFrgmtArray[ mCurFrag])
                                .commit();
                        return true;
                    case R.id.navbomitem_device:
                        mCurFrag = 2;
                        getSupportFragmentManager().beginTransaction()
                                .hide( mFrgmtArray[last]).show( mFrgmtArray[ mCurFrag])
                                .commit();
                        return true;

                    case R.id.navbomitem_my:
                        mCurFrag = 3;
                        getSupportFragmentManager().beginTransaction()
                                .hide( mFrgmtArray[last]).show( mFrgmtArray[ mCurFrag])
                                .commit();
                        return true;
                    default:
                        return false;
                }
            }
        });

        mBottomNavigationView.setSelectedItemId(R.id.navbomitem_sport);
    }
}

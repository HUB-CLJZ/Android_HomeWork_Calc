package com.example.materialdesign1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager pager = findViewById(R.id.pager);
        TabLayout tab = findViewById(R.id.tab);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(String.format(Locale.CHINA,"第%02d页",i));
        }
        pager.setAdapter(new MyAdaper(getSupportFragmentManager(),list));

        tab.setupWithViewPager(pager);
    }
}

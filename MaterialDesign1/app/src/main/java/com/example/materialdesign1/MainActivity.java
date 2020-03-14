package com.example.materialdesign1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //关联布局
        ViewPager pager = findViewById(R.id.pager);
        TabLayout tab = findViewById(R.id.tab);

        //添加数据
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(String.format(Locale.CHINA,"第%02d页",i));
        }
        //创建适配器
        MyAdaper myAdaper = new MyAdaper(getSupportFragmentManager(),list);
        //设置适配器
        pager.setAdapter(myAdaper);
        //设置可以滚动
        tab.setTabMode(TabLayout.MODE_FIXED);
        //设置viewPager
        tab.setupWithViewPager(pager);
    }
}
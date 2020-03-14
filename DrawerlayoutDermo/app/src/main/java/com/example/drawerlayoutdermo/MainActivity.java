package com.example.drawerlayoutdermo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ListView listView;
    private List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.draw_main);
        toolbar = findViewById(R.id.toos);
        listView = findViewById(R.id.my_list);
        //侧边栏的监听用add的方法添加
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Toast.makeText(MainActivity.this, "关闭了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //添加数据
        data =  new ArrayList();
        for (int i = 0; i < 20; i++) {
            data.add("这是第"+i+"个");
        }
        listView.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,data));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要用drawerLayout调用，需要用ID属性
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }
}
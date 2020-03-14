package com.example.toolbardemo;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.tool_my);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        //关联导航
        toolbar.inflateMenu(R.menu.menu);
        //设置Item的监听事件，注意androidx.appcompat.widget.Toolbar.OnMenuItemClickListener()要写全
        //只能用内部类的的方法实现
        toolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.more_type) {
                    Toast.makeText(MainActivity.this, "多条目类型", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.title_s) {
                    Toast.makeText(MainActivity.this, "标题被点击", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.list_view_vertical_stander) {
                    Toast.makeText(MainActivity.this, "垂直标准被点击", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.list_view_vertical_reverse) {
                    Toast.makeText(MainActivity.this, "垂直反向被点击", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
}
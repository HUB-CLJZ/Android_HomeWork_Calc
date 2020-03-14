package com.example.customcontroldemo1;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView tv;
    private List<Map<String,Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tvv);
        spinner = findViewById(R.id.spinner);
        //数据源
        data = new ArrayList<>();
        //创建一个SimpleAdapter适配器
        //第一个参数：上下文，第二个参数：数据源，第三个参数：item子布局，第四、五个参数：键值对，获取item布局中的控件id
        final SimpleAdapter s_adapter = new SimpleAdapter(this, getData(), R.layout.layout_item, new String[]{"image", "text"}, new int[]{R.id.img, R.id.tvv});


        //设置垂直偏移量
//        spinner.setDropDownVerticalOffset(80);
// Control and adapter binding
        //控件与适配器绑定
        spinner.setAdapter(s_adapter);
    }
    //数据源
    private List<Map<String,Object>> getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("image", R.mipmap.ic_launcher);
        map.put("text", "北京");
        data.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("image", R.mipmap.ic_launcher);
        map1.put("text", "上海");
        data.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("image", R.mipmap.ic_launcher);
        map2.put("text", "廣州");
        data.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("image", R.mipmap.ic_launcher);
        map3.put("text", "深圳");
        data.add(map3);
        return data;
    }
}
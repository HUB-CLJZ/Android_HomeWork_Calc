package com.example.applicationtionmarket;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Map<String, Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv);
        initDataList();
        // key值数组，适配器通过key值取value，与列表项组件一一
        //
        //
        // 对应
        String[] from = {"iv_img","tv_text"};
        // 列表项组件Id 数组
        int[] to = {R.id.iv_img,R.id.tv_text};
        final SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.list_iteam, from, to);
        listView.setAdapter(adapter);
    }
    private void initDataList() {
        //图片资源
        int img_rank[] = { R.drawable.jd, R.drawable.qq, R.drawable.dz,R.drawable.xl,R.drawable.tm,R.drawable.uc,R.drawable.wx};
        String dataTop[] = {"京东商场","QQ","QQ斗地主","新浪微博","天猫","UC浏览器","微信"};
        list = new ArrayList<>();
        for (int i = 0; i < dataTop.length; i++) {
            Map<String, Object> map = new HashMap();
            map.put("iv_img", img_rank[i]);
            map.put("tv_text", dataTop[i]);
            list.add(map);
        }
    }
}
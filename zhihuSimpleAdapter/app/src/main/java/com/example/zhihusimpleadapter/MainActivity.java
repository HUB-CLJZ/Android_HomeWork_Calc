package com.example.zhihusimpleadapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
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

        // key值数组，适配器通过key值取value，与列表项组件一一对应
        String[] from = {"top","title","heat","photo"};
        // 列表项组件Id 数组
        int[] to = { R.id.top, R.id.title, R.id.heat, R.id.photo};

        /**
         * SimpleAdapter(Context context, List<?xtendMap<String?>> data, int resource, String[] from, int[] to)
         * context：activity界面类
         * data 数组内容是map的集合数据
         * resource 列表项文件
         * from map key值数组
         * to 列表项组件id数组      from与to一一对应，适配器绑定数据
         */
        final SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.listview_item, from, to);

        listView.setAdapter(adapter);
        /**
         * 单击
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Map<String, Object> map = list.get(arg2);
                String str = "";
                str += map.get("top") + "---" + map.get("title") + "\n"
                        + "长按删除！";
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT)
                        .show();

            }
        });
        /**
         * 长按
         */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                list.remove(arg2);
                adapter.notifyDataSetChanged();// 更新列表数据
                Toast.makeText(MainActivity.this, "删除成功！", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }
    /**
     * 初始化适配器需要的数据格式
     */
    private void initDataList() {
        //图片资源
        int img_rank[] = { R.drawable.rank1, R.drawable.rank2, R.drawable.rank3,
                R.drawable.rank4, R.drawable.rank5, R.drawable.rank6, R.drawable.rank7,
                R.drawable.rank8, R.drawable.rank9, R.drawable.rank10 };

        int img_photo[] = { R.drawable.p1, R.drawable.p2, R.drawable.p3,
                R.drawable.p4, R.drawable.p5,0,0, R.drawable.p8, R.drawable.p9,
                R.drawable.p10 };

        String dataTop[] = {"如何看待美军首次回应网传 UFO：视频为真，现有人类飞行技术无法达到？",
                            "如何看待李嘉诚的文章《李嘉诚公开回复：不要用那些空洞的道德来衡量我，我只是一个纯粹的商人》？",
                            "电影《杀人回忆》的故事原型真凶疑似被发现，会对案件发展和影视作品有什么影响?",
                            "为什么那么多老师呼吁取消教师节？   ",
                            "如何评价周杰伦新歌《说好不哭》豆瓣评分跌下 5.8 分?",
                            "如何看待马云最后一个工作日被记旷工并扣发当月全勤奖这件事？",
                            "你见过的最没见过世面的男孩子是什么样子的？",
                            "有哪些越早知道越好的人生经验？           ",
                            "哔哩哔哩有哪些值得推荐的 UP 主及其代表作？",
                            "如何看待警方通报涠洲岛失联 22 岁女教师已确认身亡？两起失踪案件中有哪些值得关注的信息？"};
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap();
            map.put("top", img_rank[i]);
            map.put("title", dataTop[i]);
            map.put("photo", img_photo[i]);
            map.put("heat", "热度"+(i+(int)(Math.random()*10000))+"万");
            list.add(map);
        }

    }
}

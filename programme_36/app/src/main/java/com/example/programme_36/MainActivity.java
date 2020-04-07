package com.example.programme_36;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Integer> mIcon = new ArrayList<>();
    private List<Integer> mMoney = new ArrayList<>();
    private List<String> mPlace = new ArrayList<>();

    //景点介绍
    private List<String> mIntroduce = new ArrayList<>();


    //景点评分
    private List<Integer> mScore = new ArrayList<>();

    //联系电话
    private List<Integer> mPhone = new ArrayList<>();


    private MyAdaper myAdaper;
    private GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //添加数据
        addData();

        myAdaper = new MyAdaper(this, mIcon, mMoney, mPlace);

        grid.setAdapter(myAdaper);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("introduce", mIntroduce.get(position));
                intent.putExtra("score", mScore.get(position));
                intent.putExtra("phone", mPhone.get(position));
                intent.putExtra("icon", mIcon.get(position));
                startActivity(intent);
            }
        });

    }

    private void addData() {
        //景点图片
        mIcon.add(R.drawable.icon_1);
        mIcon.add(R.drawable.icon_2);
        mIcon.add(R.drawable.icon_3);
        mIcon.add(R.drawable.icon_4);

        //景点价格
        mMoney.add(12);
        mMoney.add(30);
        mMoney.add(70);
        mMoney.add(50);

        //景点名称
        mPlace.add("故宫");
        mPlace.add("蜗台");
        mPlace.add("天坛");
        mPlace.add("黄鹤楼");

        //景点点评
        mIntroduce.add("北京故宫是中国明清两代的皇家宫殿，旧称紫禁城，位于北京中轴线的中心。北京故宫以三大殿为中心，占地面积72万平方米，建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。");
        mIntroduce.add("在中美洲古代玛雅人的奇钦·伊查遗址中，有一座著名的天文观象台，这座筑在高台上呈蜗形的玛雅人古天文观象台，称“蜗台”，亦被译为“蜗牛塔”。");
        mIntroduce.add("天坛，在北京市南部，东城区永定门内大街东侧。占地约273万平方米。天坛始建于明永乐十八年（1420年），清乾隆、光绪时曾重修改建。为明、清两代帝王祭祀皇天、祈五谷丰登之场所。");
        mIntroduce.add("黄鹤楼位于湖北省武汉市长江南岸的武昌蛇山之巅，濒临万里长江，是国家5A级旅游景区，“江南三大名楼”之一，自古享有“天下江山第一楼“和“天下绝景”之称。黄鹤楼是武汉市标志性建筑，与晴川阁、古琴台并称“武汉三大名胜”。");

        //景点评分
        mScore.add(5);
        mScore.add(4);
        mScore.add(3);
        mScore.add(2);

        //联系电话
        mPhone.add(121412412);
        mPhone.add(16161616);
        mPhone.add(541641864);
        mPhone.add(1616161616);
    }

    private void initView() {
        grid = findViewById(R.id.grid);
    }
}

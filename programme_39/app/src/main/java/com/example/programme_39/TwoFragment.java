package com.example.programme_39;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {

    String datas[] = {"如何看待美军首次回应网传 UFO：视频为真，现有人类飞行技术无法达到？",
            "如何看待李嘉诚的文章《李嘉诚公开回复：不要用那些空洞的道德来衡量我，我只是一个纯粹的商人》？",
            "有哪些越早知道越好的人生经验？           ",
            "为什么那么多老师呼吁取消教师节？   ",
            "如何评价周杰伦新歌《说好不哭》豆瓣评分跌下 5.8 分?",
            "如何看待马云最后一个工作日被记旷工并扣发当月全勤奖这件事？",
            "你见过的最没见过世面的男孩子是什么样子的？",
            "电影《杀人回忆》的故事原型真凶疑似被发现，会对案件发展和影视作品有什么影响?",
            "哔哩哔哩有哪些值得推荐的 UP 主及其代表作？",
            "如何看待警方通报涠洲岛失联 22 岁女教师已确认身亡？两起失踪案件中有哪些值得关注的信息？"};
    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        ListView listView = view.findViewById(R.id.list);

        listView.setAdapter(new ArrayAdapter<String >(getContext(),R.layout.support_simple_spinner_dropdown_item,datas));


        return view;
    }

}

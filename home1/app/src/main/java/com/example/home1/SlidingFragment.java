package com.example.home1;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
/**
 * @author admin
 */
public class SlidingFragment extends Fragment {
    private ViewPager mLooperPager;
    private SlidingAdaper mAdapter;
    private Button btn;
    private List<Integer> sColer = new ArrayList<>();
    public SlidingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sliding,null);
        mLooperPager = view.findViewById(R.id.looper_pager);

        //添加颜色，红黄蓝
        mAdapter = new SlidingAdaper();
        sColer.add(Color.RED);
        sColer.add(Color.BLUE);
        sColer.add(Color.YELLOW);
        mAdapter.setData(sColer);
        btn = view.findViewById(R.id.jump);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),TabLayout_item.class));
            }
        });
        mLooperPager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
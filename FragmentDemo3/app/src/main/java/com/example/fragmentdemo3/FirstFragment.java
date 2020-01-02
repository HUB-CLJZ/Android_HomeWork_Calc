package com.example.fragmentdemo3;


import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.MainThread;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_first,null);
       //得到按钮
        Button btn_test = view.findViewById(R.id.btn_test);
        //添加点击事件
        //这里提一句，fragment里的事件只能通过得到id的这种方式添加。
        //不能使用在布局里设置onclick属性的方式
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"测试成功！", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}

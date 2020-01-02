package com.example.fragmentdemo5;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FirstFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first,null);
        Button btn_update = view.findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在SecondFragment里定义了update方法，通过这个方法进行对其进行ui的更信
                //但要注意，这里不能自己创建SecondFragment对象然后调用这个方法。
                //这样会抛异常，因为我们自己创建的对象，里面的onCreateView方法是不会被调用的，
                //然后控件就没初始化。
                //所以我们得通过fragment之间的桥梁Activity拿到SecondFragment的实例

                //通过桥梁拿到fragment管理者，然后通过fragment的标识拿到SecondFragment实例
                SecondFragment f2 = (SecondFragment) getActivity().getSupportFragmentManager()
                        .findFragmentByTag("f2");
                f2.update("哈哈哈哈哈哈");
            }
        });
        return view;
    }
}

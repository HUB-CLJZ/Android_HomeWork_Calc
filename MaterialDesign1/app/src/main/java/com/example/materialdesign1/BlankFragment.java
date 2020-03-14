package com.example.materialdesign1;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author admin
 *
 */
public class BlankFragment extends Fragment {
    public BlankFragment() {
        // Required empty public constructor
    }
    //实例化Fragment
    public static BlankFragment newInstance(String text) {
        //传递数据
        Bundle args = new Bundle();
        args.putString("text",text);
        BlankFragment fragment = new BlankFragment();
        //设置fragment的参数
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //布局转化为View对象
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置数据
        TextView textView = view.findViewById(R.id.pager_text);
        String str = getArguments().getString("text");
        textView.setText(str);
    }


}
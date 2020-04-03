package com.example.programme_04;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment {
    private ImageView imageView;

    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        imageView = view.findViewById(R.id.iv_photo1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(v.getContext(),PhotoActivity.class);
               intent.putExtra("photo_url","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585766253731&di=86277fa92e7c980ba57e00636d41ae4a&imgtype=0&src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F08%2F01%2F147002428342837531.jpeg");
               startActivity(intent);
            }
        });
        return view;
    }

}

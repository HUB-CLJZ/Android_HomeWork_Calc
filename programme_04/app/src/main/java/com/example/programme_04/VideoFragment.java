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
public class VideoFragment extends Fragment {
    private ImageView imageView;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_video, null);
        imageView = view.findViewById(R.id.iv_video1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),VideoActivity.class);
                intent.putExtra("video_url","http://www.chachaba.com/news/uploads/media/190415/6055-1Z415135934.mp4");
                startActivity(intent);
            }
        });
        return view;
    }

}

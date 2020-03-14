package com.example.competition_dislocation8;
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
    private ImageView img_jump;

    public VideoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video,null);
        img_jump = view.findViewById(R.id.jump);
        img_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),VideoActivity.class));
            }
        });
        return view;
    }
}
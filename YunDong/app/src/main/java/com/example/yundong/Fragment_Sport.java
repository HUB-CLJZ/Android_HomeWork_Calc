package com.example.yundong;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Sport.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Sport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Sport extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View mVw1,mVw2;
    private List<View> mVwList;
    private ViewPager mVwPage;
    private Switch mReceiverSwitch;
    private Button mBtnPlay,mBtnPause,mBtnStop;


    public Fragment_Sport() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Sport.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Sport newInstance(String param1, String param2) {
        Fragment_Sport fragment = new Fragment_Sport();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment__sport, container, false);

        mBtnPlay = root.findViewById(R.id.button_audio_play);
        mBtnPause = root.findViewById(R.id.button_audio_pause);
        mBtnStop = root.findViewById(R.id.button_audio_stop);
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Sport.this.mListener.onFragCmdCalled(AppConfig.MUSIC_PLAY,null);
            }
        });
        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Sport.this.mListener.onFragCmdCalled(AppConfig.MUSIC_PAUSE,null);
            }
        });
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Sport.this.mListener.onFragCmdCalled(AppConfig.MUSIC_STOP,null);
            }
        });

        mReceiverSwitch = root.findViewById(R.id.switch_nocall);
        mReceiverSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AppConfig.Sport_NO_CALL = b;
            }
        });

        mVwPage = root.findViewById(R.id.viewPager_one);
        mVw1 =inflater.inflate(R.layout.layout_viewpage1,null);
        mVw2 = inflater.inflate(R.layout.layout_viewpage2,null);
        mVwList = new ArrayList<View>();
        mVwList.add(mVw1);
        mVwList.add(mVw2);

        mVwPage.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mVwList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }
            @Override
            //销毁指定的页面
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(mVwList.get(position));
            }

            @NonNull
            @Override
            //实例化指定位置页面 并将其添加到容器中
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(mVwList.get(position));
                return mVwList.get(position);
            }
        });

        root.findViewById(R.id.frgmt0_buttontest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallMainActivity(Uri.parse("hello nihao from Fragment_Sport"));
            }
        });
        mVw1.findViewById(R.id.textView_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//打开城市管理页面
                Intent intent = new Intent("com.example.yundong.SHOW_City_Activity");
                startActivity(intent);

            }
        });
        mVw1.findViewById(R.id.button_viewpage_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//下一个城市
                Toast.makeText(Fragment_Sport.this.getContext(),"ViewPage1的按钮事件测试程序",Toast.LENGTH_SHORT).show();
            }
        });
        mVw1.findViewById(R.id.button_viewpage_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//上一个城市

            }
        });
        return root;
    }

    // 调用主窗口方法
    public void onCallMainActivity(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        Bundle onFragCmdCalled(int cmd,Bundle bundle);

    }

    public void updateTianqi(List<Map<String,String>> tianqiList){
        TextView textView = mVw1.findViewById(R.id.textView_city);
        textView.setText("haha");
    }
}

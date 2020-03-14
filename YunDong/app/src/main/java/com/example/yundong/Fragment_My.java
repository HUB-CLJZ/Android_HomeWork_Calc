package com.example.yundong;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_My.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_My#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_My extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Switch mSwsavename;

    public Fragment_My() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_My.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_My newInstance(String param1, String param2) {
        Fragment_My fragment = new Fragment_My();
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
        View fmroot = inflater.inflate(R.layout.fragment__my, container, false);
        mSwsavename = fmroot.findViewById(R.id.switch_save_namepasswd);
        mSwsavename.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //SWITCH组件的选中状态变化监听事件，将最新状态存入SP文件
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences sp = getContext().getSharedPreferences(AppConfig.KEYSTR_APPCFG_FILENAME,Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putBoolean(AppConfig.KEYSTR_AUTOSAVENAME,b);
                ed.commit();
                //更新全局参数配置
                AppConfig.Config_AutoSaveUsername = b;
            }
        });
        //通过SP文件，获取APP的配置，如自动保存用户名
        SharedPreferences sp = getContext().getSharedPreferences(AppConfig.KEYSTR_APPCFG_FILENAME,Context.MODE_PRIVATE);
        mSwsavename.setChecked(sp.getBoolean(AppConfig.KEYSTR_AUTOSAVENAME,false));
        //更新全局参数配置
        AppConfig.Config_AutoSaveUsername = sp.getBoolean(AppConfig.KEYSTR_AUTOSAVENAME,false);

        return fmroot;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
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
}

package com.example.yundong;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Device.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Device#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Device extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView mListviewDevice;

    public Fragment_Device() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Device.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Device newInstance(String param1, String param2) {
        Fragment_Device fragment = new Fragment_Device();
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
        View root = inflater.inflate(R.layout.fragment__device, container, false);

        //ListView中显示设备一览代码，准备数据，设置适配器
        final List<Map<String,Object>> listdata = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("name", "音箱");
        map1.put("type", "播放设备");
        map1.put("pic", R.drawable.device_icon04);
        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("name", "Apple iPod");
        map2.put("type", "播放设备");
        map2.put("pic", R.drawable.device_icon02);
        Map<String,Object> map3 = new HashMap<String,Object>();
        map3.put("name", "头盔式耳机");
        map3.put("type", "耳机");
        map3.put("pic", R.drawable.device_icon03);
        listdata.add(map1);
        listdata.add(map2);
        listdata.add(map3);

        //本部分从文件中读json格式设备信息，与上面数组信息相同
        FileInputStream fin;
        byte[] buf;
        JSONArray jsonArray;
        JSONObject jsonObject;
        String jsonStr="";
        listdata.clear();//清空数组设置的list元素
        try {
            fin = getContext().openFileInput("devlistinfo_json.txt");
            buf = new byte[fin.available()];
            fin.read(buf);
            if(buf.length>0)
                jsonStr = new String(buf);
            fin.close();
            jsonArray = new JSONArray(jsonStr);

            for(int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                Map<String,Object> tmpmap= new HashMap<String,Object>();
                tmpmap.put("name", jsonObject.getString("name"));
                tmpmap.put("type", jsonObject.getString("type"));
                tmpmap.put("pic",  jsonObject.getInt("pic"));
                listdata.add(tmpmap);
            }
        }catch (Exception e){
        }

        mListviewDevice = root.findViewById(R.id.frgmt_device_listview);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listdata,R.layout.listview_device_item,
                new String[]{"name","type","pic"},new int[]{R.id.lvdev_item_devname,R.id.lvdev_item_devtype,R.id.lvdev_item_imgview});
        mListviewDevice.setAdapter(simpleAdapter);

        //测试接口按钮事件处理代码
        root.findViewById(R.id.frgmt2_button_devinfosavetofile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存文件知识点：listdata.toString()将设备信息转换为字符串，然后写入文件中
                onButtonPressed(Uri.parse("文件 "+AppConfig.FILENAME_DEVINFO_TXT+" 中写入："+listdata.toString()));
                FileOutputStream fos;
                try {
                    fos = getContext().openFileOutput(AppConfig.FILENAME_DEVINFO_TXT,Context.MODE_PRIVATE);
                    fos.write(listdata.toString().getBytes());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return root;
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

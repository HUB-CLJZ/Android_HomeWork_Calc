package com.example.station56;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private String[] mStations = {"站1","站2","站3","站4","站5","站6","站7","站8","站9","站10",
            "站11","站12","站13","站14","站15","站16","站17","站18","站19","站20",
            "站21","站22","站23","站24","站25",};
    private ListView mListView;
    private List<Map<String,String>> mListData = new ArrayList<Map<String, String>>();
    private int mLastClickPositon=-1;
    private TextView mTxtviewItemLastSelected;
    private MyViewAdapter mAdapter ;
    class MyViewAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        public MyViewAdapter(Context context){
            mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return mStations.length;
        }

        @Override
        public Object getItem(int i) {
            return mStations[i];
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                holder = new ViewHolder();
                view = mInflater.inflate(R.layout.listview_item, null);
                holder.name = view.findViewById(R.id.textView_name);
                holder.myID = view.findViewById(R.id.textView_id);
                view.setTag(holder);
            }else{
                holder = (ViewHolder)view.getTag();
            }
            holder.name.setText(mStations[i]);
            holder.myID.setText(String.valueOf(i+1));//此处有坑，不能用整数索引，会当成资源ID，导致程序崩掉
            if(mLastClickPositon==i) {
                holder.myID.setBackgroundResource(R.drawable.textview_shape_selected);
                //此处一定要重新记录最后设置选中item的引用，因为缓冲因素，来回滚动几次后，显示position位置的控件已经发生了变化，切记切记
                mTxtviewItemLastSelected = holder.myID;
            }
            else
                holder.myID.setBackgroundResource(R.drawable.textview_shape);
            return view;
        }
        public final class ViewHolder{

            public TextView myID;
            public TextView name;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.listview);

        //此适配器不能用在该项目，因为有item重用机制，会导致多选，而且无法改
        //SimpleAdapter simpleAdapter = new SimpleAdapter(this,mListData,R.layout.listview_item,from,to);


        mAdapter = new MyViewAdapter(this);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,view.toString() + "---" +i+"---"+l,Toast.LENGTH_SHORT).show();
                //先把上次的设置为未选择，再
                if(mTxtviewItemLastSelected!=null){
                    mTxtviewItemLastSelected.setBackgroundResource(R.drawable.textview_shape);
                }
                mTxtviewItemLastSelected = view.findViewById(R.id.textView_id);
                mTxtviewItemLastSelected.setBackgroundResource(R.drawable.textview_shape_selected);
                mLastClickPositon = i;
            }
        });

    }
}

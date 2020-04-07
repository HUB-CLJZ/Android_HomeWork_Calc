package com.example.programme_56;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] mStations = {"巨龙大道","宋家岗","航空总部","建设大道双墩","建设大道新合村","解放大道水厂","解放大道太平洋","建设大道双墩","建设大道新合村","解放大道水厂","建设大道双墩","建设大道新合村","解放大道水厂"};
    private ListView mListView;

    //最后选中的数据
    private int mPosition = -1;
    private TextView mRecordTextView;
    private MyViewAdapter mAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listview);

        mAdapter = new MyViewAdapter(this);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //先把上次的设置为未选择
                if(mRecordTextView != null) {
                    mRecordTextView.setBackgroundResource(R.drawable.textview_shape);
                }
                //重新寻找控件，记录位置
                mRecordTextView = view.findViewById(R.id.textView_id);
                mRecordTextView.setBackgroundResource(R.drawable.textview_shape_selected);
                mPosition = i;
            }
        });
    }

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
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.name.setText(mStations[i]);

            //此处有坑，不能用整数索引，会当成资源ID，导致程序崩掉
            holder.myID.setText(String.valueOf(i+1));
            if(mPosition == i) {
                holder.myID.setBackgroundResource(R.drawable.textview_shape_selected);
                //此处一定要重新记录最后设置选中item的引用，因为缓冲因素，来回滚动几次后，显示position位置的控件已经发生了变化，切记切记
                mRecordTextView = holder.myID;
            } else {
                holder.myID.setBackgroundResource(R.drawable.textview_shape);
            }
            return view;
        }
        public final class ViewHolder{
            public TextView myID;
            public TextView name;
        }
    }
}
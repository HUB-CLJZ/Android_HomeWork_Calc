package com.example.programme_31;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdaper extends BaseAdapter {

    private List<String> mTitles;
    private List<String> mContexts;
    private List<String> mTimes;
    private Context mContext;

    public MyAdaper(Context context,List<String> mTitles,List<String> mContexts,List<String> mTimes) {
        this.mContext = context;
        this.mTitles = mTitles;
        this.mContexts = mContexts;
        this.mTimes = mTimes;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public Object getItem(int position) {
        return mTitles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext,R.layout.item_layout,null);

        TextView title = view.findViewById(R.id.tv_title);
        TextView time = view.findViewById(R.id.tv_time);
        TextView content = view.findViewById(R.id.tv_content);

        title.setText(title.getText().toString()+mTitles.get(position));
        time.setText(time.getText().toString()+mTimes.get(position));
        content.setText(content.getText().toString()+mContexts.get(position));

        return view;
    }
}

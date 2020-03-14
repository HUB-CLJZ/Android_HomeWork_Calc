package com.example.searchhistorydemo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LinerAdaper extends BaseAdapter {

    List<String> datas = new ArrayList<>();

    public LinerAdaper(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(parent.getContext(),R.layout.item_layout,null);


        TextView information = view.findViewById(R.id.tv_information);

        information.setText(datas.get(position));

        return view;
    }
}

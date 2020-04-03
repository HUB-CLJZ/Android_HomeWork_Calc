package com.example.programme_02;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdaper extends BaseAdapter {
    private List<Data> datas;

    public ListAdaper(List<Data> datas) {
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
        convertView = View.inflate(parent.getContext(),R.layout.item_layout,null);
        //初始化控件
        TextView road = convertView.findViewById(R.id.road);
        TextView red_lamp = convertView.findViewById(R.id.red_lamp);
        TextView yellow_lamp = convertView.findViewById(R.id.yellow_lamp);
        TextView green_lamp = convertView.findViewById(R.id.green_lamp);
        //获取数据
        Data data = datas.get(position);
        //填充数据
        road.setText(data.getCrossroads()+"");
        red_lamp.setText(data.getRed_lamp()+"");
        yellow_lamp.setText(data.getYello_lamp()+"");
        green_lamp.setText(data.getGreen_lamp()+"");
        return convertView;
    }
}

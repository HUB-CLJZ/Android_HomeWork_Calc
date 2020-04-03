package com.example.programme_03;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Car> mCars;
    private Context context;
    public MyAdapter(List<Car> cars,Context context) {
        this.mCars = cars;
        this.context = context;
    }
    @Override
    public int getCount() {
        return mCars.size();
    }

    @Override
    public Object getItem(int position) {
        return mCars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context,R.layout.item_tab,null);
        //初始化控件
        TextView order = convertView.findViewById(R.id.order);
        TextView carNumber = convertView.findViewById(R.id.car_num);
        TextView applyMoney = convertView.findViewById(R.id.applyMoney);
        TextView time = convertView.findViewById(R.id.time);
        //获得当前的数据
        Car car = mCars.get(position);
        //填充数据
        order.setText(position+1+"");
        carNumber.setText(car.getNumber()+"");
        applyMoney.setText(car.getRecharge());
        time.setText(car.getTime());
        return convertView;
    }
}

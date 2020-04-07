package com.example.programme_36;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdaper extends BaseAdapter {

    private List<Integer> mIcon;
    private List<Integer> mMoney;
    private List<String> mPlace;
    private Context mContext;

    public MyAdaper(Context context, List<Integer> mIcon, List<Integer> mMoney, List<String> mPlace) {
        this.mContext = context;
        this.mIcon = mIcon;
        this.mMoney = mMoney;
        this.mPlace = mPlace;
    }

    @Override
    public int getCount() {
        return mIcon.size();
    }

    @Override
    public Object getItem(int position) {
        return mIcon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext,R.layout.grid_item,null);

        ImageView icon = view.findViewById(R.id.iv_icon);
        TextView money = view.findViewById(R.id.tv_money);
        TextView place = view.findViewById(R.id.tv_place);

        money.setText(money.getText().toString()+mMoney.get(position));
        place.setText(place.getText().toString()+mPlace.get(position));
        icon.setImageResource(mIcon.get(position));
        return view;
    }
}

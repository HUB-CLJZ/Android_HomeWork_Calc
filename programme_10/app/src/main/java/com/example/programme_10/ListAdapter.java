package com.example.programme_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<String> times;
    private List<String> distances;
    private LayoutInflater mInflater;
    public ListAdapter(Context context,List<String> times, List<String> distances) {
        this.times = times;
        this.distances = distances;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return times.size();
    }

    @Override
    public Object getItem(int position) {
        return times.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_layout, null);

        TextView arrival_time = view.findViewById(R.id.arrival_time);
        TextView distance = view.findViewById(R.id.distance);

        arrival_time.setText(times.get(position));
        distance.setText(distances.get(position));

        return view;
    }
}
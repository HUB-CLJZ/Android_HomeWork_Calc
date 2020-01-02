package com.example.recycleview1.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.recycleview1.R;
import com.example.recycleview1.beans.ItemBean;

import java.util.List;

/**
 * @author admin
 */
public class ListViewAdapter extends RecyclerViewBaseAdapter {

    public ListViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_list_view,null);
        return view;
    }
}
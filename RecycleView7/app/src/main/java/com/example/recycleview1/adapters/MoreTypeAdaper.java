package com.example.recycleview1.adapters;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview1.R;
import com.example.recycleview1.beans.MoreTypeBean;
import java.util.List;
public class MoreTypeAdaper extends RecyclerView.Adapter {
    private List<MoreTypeBean> mData;
    public static final int TYPE_FULL_IMAGE = 0;
    public static final int TYPE_RIGHT_IMAGE = 1;
    public static final int TYPE_THREE_IMAGES = 2;
    public MoreTypeAdaper(List<MoreTypeBean> mData) {
        this.mData = mData;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = View.inflate(parent.getContext(), R.layout.item_type_full_image,null);
            return new FullImageHolder(view);
        } else if (viewType == 1) {
            view = View.inflate(parent.getContext(), R.layout.item_type_left_title_right_image,null);
            return new FullImageHolder(view);
        } else {
            view = View.inflate(parent.getContext(), R.layout.item_type_three_image,null);
            return new FullImageHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_FULL_IMAGE;
        } else if (position == 1) {
            return TYPE_RIGHT_IMAGE;
        } else {
            return TYPE_THREE_IMAGES;
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    private class FullImageHolder extends RecyclerView.ViewHolder {
        public FullImageHolder(View itemView) {
            super(itemView);
        }
    }
}
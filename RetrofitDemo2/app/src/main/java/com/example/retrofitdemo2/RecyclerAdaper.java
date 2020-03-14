package com.example.retrofitdemo2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdaper extends RecyclerView.Adapter<RecyclerAdaper.ViewHolder> {
    private Context mContent;

    private List<JsonResult> mData;

    public RecyclerAdaper(List<JsonResult> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContent = parent.getContext();

        //创建视图
        View view = View.inflate(parent.getContext(),R.layout.item_layout,null);
        //返回给ViewHolder
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView name;
        private TextView password;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_icon);
            name = itemView.findViewById(R.id.tv_name);
            password = itemView.findViewById(R.id.tv_password);
        }

        public void setData(JsonResult jsonResult) {

            //加载图片
            Glide.with(mContent)
                    .load(jsonResult.getUser_image())
                    .into(icon);
            name.setText(jsonResult.getUser_name());
            password.setText(jsonResult.getPassword());
        }
    }
}

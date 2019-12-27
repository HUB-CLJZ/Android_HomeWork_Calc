package com.example.getwebdata;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.loopj.android.image.SmartImageView;

public class NewsAdapter extends BaseAdapter {
    private String[] user_names;
    private String[] passwords;
    private String[] user_images;
    private Context mContext;
    public NewsAdapter(Context mContext, String[] user_images, String[] user_names, String[] passwords) {
        this.mContext = mContext;
        this.user_images = user_images;
        this.user_names = user_names;
        this.passwords = passwords;
    }
    @Override
    public int getCount() {
        return user_images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_iteam,null);
            holder = new ViewHolder();
            holder.user_name = convertView.findViewById(R.id.user_name);
            holder.password = convertView.findViewById(R.id.password);
            holder.user_image = convertView.findViewById(R.id.user_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.user_name.setText(user_names[position]);
        holder.password.setText(passwords[position]);

        DownImage downImage = new DownImage(user_images[position]);
        final ViewHolder finalHolder = holder;
        downImage.loadImage(new DownImage.ImageCallBack() {
            @Override
            public void getDrawable(Drawable drawable) {
                finalHolder.user_image.setImageDrawable(drawable);
            }
        });
        return convertView;
    }

    static class ViewHolder{
        TextView user_name;
        TextView password;
        ImageView user_image;
    }

}

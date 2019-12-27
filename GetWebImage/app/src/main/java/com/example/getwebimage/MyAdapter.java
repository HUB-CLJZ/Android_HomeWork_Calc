package com.example.getwebimage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> list;

    public MyAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view  = LayoutInflater.from(context).inflate(R.layout.view_iteam, null);

            holder.user_name = view.findViewById(R.id.user_name);
            holder.password = view.findViewById(R.id.password);
            holder.user_image = view.findViewById(R.id.user_image);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.user_name.setText(list.get(i).get("user_name").toString());
        holder.password.setText(list.get(i).get("password").toString());
        String urlPath = list.get(i).get("user_image").toString();
        final String urlInto = urlPath;
        Log.d("CLJZ", "getView: "+urlInto);
        RequestOptions options = new RequestOptions()
                .override(50, 50);
        Glide.with(context)
             .load(urlInto)
             .apply(options)
             .into(holder.user_image);
        //holder.user_image.setImageUrl(list.get(i).get("user_image").toString());
        //holder.user_image.setImageResource(R.drawable.ic_launcher_background);
        //接口回调的方法，完成图片的读取;
//        DownImage downImage = new DownImage(list.get(i).get("user_image").toString());
//        downImage.loadImage(new DownImage.ImageCallBack() {
//            @Override
//            public void getDrawable(Drawable drawable) {
//                holder.user_image.setImageDrawable(drawable);
//            }
//        });

        return view;
    }


    class ViewHolder{
        TextView user_name;
        TextView password;
        ImageView user_image;
    }
}

package com.example.viewpager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;
/**
 * @author admin
 */
public class MyPagerAdapter extends PagerAdapter {
    private List<Integer> mColer = null;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setBackgroundColor(mColer.get(position));
        //将该View添加进容器里。
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        if (mColer != null) {
            return mColer.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setData(List<Integer> sColer) {
        this.mColer = sColer;
    }
}

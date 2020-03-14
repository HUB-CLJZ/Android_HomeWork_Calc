package com.example.home1;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.List;
/**
 * @author admin
 *
 */
public class SlidingAdaper extends PagerAdapter {
    private List<Integer> mColer = new ArrayList<>();

    @Override
    public int getCount() {
        return mColer.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView  = new ImageView(container.getContext());
        imageView.setBackgroundColor(mColer.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setData(List<Integer> sColer) {
        this.mColer = sColer;
    }
}
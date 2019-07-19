package com.github.groupiedemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * banner的适配器
 */
public class BannerAdapter extends PagerAdapter {
    private List<String> urls = new ArrayList<>();
    private Context context;

    public BannerAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View v, @NonNull Object o) {
        return v == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if (urls.size() > 0) {
            String url = urls.get(position % urls.size());

            // 测试用本地图片
            img.setImageResource(Integer.parseInt(url));
            // 此处应该设置网络图片
           /* GlideApp.with(context)
                    .asBitmap()
                    .load(url)
                    .into(img);*/
        }

        // 加载到容器
        container.addView(img);

        return img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void update(List<String> images) {
        this.urls.clear();
        this.urls.addAll(images);
        notifyDataSetChanged();
    }
}

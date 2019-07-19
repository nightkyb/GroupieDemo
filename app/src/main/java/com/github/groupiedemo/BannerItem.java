package com.github.groupiedemo;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager.widget.ViewPager;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author nightkyb created at 2019/6/20 11:31
 */
public class BannerItem extends Item<BannerItem.ItemViewHolder> {
    private List<String> images = new ArrayList<>();
    private LifecycleOwner lifecycleOwner;
    private Disposable bannerDisposable;
    private ItemViewHolder viewHolder;

    private LifecycleObserver lifecycleObserver = new DefaultLifecycleObserver() {
        @Override
        public void onStart(@NonNull LifecycleOwner owner) {
            Log.i("tag", "onStart");
            startBanner();
        }

        @Override
        public void onStop(@NonNull LifecycleOwner owner) {
            Log.i("tag", "onStop");
            stopBanner();
        }
    };

    public BannerItem(List<String> images, LifecycleOwner lifecycleOwner) {
        super();
        this.images.clear();
        this.images.addAll(images);
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public int getLayout() {
        return R.layout.item_banner;
    }

    @NonNull
    @Override
    public ItemViewHolder createViewHolder(@NonNull View itemView) {
        return new ItemViewHolder(itemView);
    }

    @Override
    public void bind(@NonNull final ItemViewHolder viewHolder, int position) {
        this.viewHolder = viewHolder;
        viewHolder.bind(images);
        lifecycleOwner.getLifecycle().addObserver(lifecycleObserver);
        startBanner();
    }

    @Override
    public void unbind(@NonNull ItemViewHolder holder) {
        lifecycleOwner.getLifecycle().removeObserver(lifecycleObserver);
        stopBanner();
        super.unbind(holder);
    }

    public void startBanner() {
        if (!images.isEmpty() && bannerDisposable == null) {
            Log.i("tag", "startBanner");
            bannerDisposable = Observable.interval(2000, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            Log.i("tag", "accept:" + aLong);
                            if (viewHolder != null) {
                                viewHolder.vpBanner.setCurrentItem(viewHolder.vpBanner.getCurrentItem() + 1);
                            }
                        }
                    });
        }
    }

    public void stopBanner() {
        if (bannerDisposable != null) {
            Log.i("tag", "stopBanner");
            bannerDisposable.dispose();
            bannerDisposable = null;
        }
    }

    static class ItemViewHolder extends ViewHolder {
        private ViewPager vpBanner;
        private LinearLayout llDot;
        private BannerAdapter bannerAdapter;
        private List<ImageView> dots = new ArrayList<>();

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            vpBanner = itemView.findViewById(R.id.vp_banner);
            llDot = itemView.findViewById(R.id.ll_dot);

            bannerAdapter = new BannerAdapter(itemView.getContext());
            vpBanner.setAdapter(bannerAdapter);
            vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    for (int i = 0; i < dots.size(); i++) {
                        ImageView dot = dots.get(i);

                        if (i == position % dots.size()) {
                            dot.setImageResource(R.drawable.banner_dot_focus);
                        } else {
                            dot.setImageResource(R.drawable.banner_dot_normal);
                        }
                    }
                }

                @Override
                public void onPageSelected(int position) {
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }

        void bind(List<String> images) {
            initDot(images);
            bannerAdapter.update(images);
        }

        /**
         * 初始化banner圆点指示器
         */
        private void initDot(List<String> images) {
            llDot.removeAllViews();
            dots.clear();
            int currentPosition = vpBanner.getCurrentItem();

            for (int i = 0; i < images.size(); i++) {
                ImageView dot = new ImageView(getRoot().getContext());

                if (i == currentPosition % images.size()) {
                    dot.setImageResource(R.drawable.banner_dot_focus);
                } else {
                    dot.setImageResource(R.drawable.banner_dot_normal);
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
                layoutParams.setMargins(8, 0, 8, 16);
                // 加载到布局容器
                llDot.addView(dot, layoutParams);
                dots.add(dot);
            }
        }
    }
}

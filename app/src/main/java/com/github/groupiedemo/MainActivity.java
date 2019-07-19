package com.github.groupiedemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.Section;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private GroupAdapter adapter;
    private RecyclerView rvTopic;
    private Disposable disposable;
    private MainPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabOn = findViewById(R.id.fab_on);
        fabOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadData();
            }
        });

        FloatingActionButton fabOff = findViewById(R.id.fab_off);
        fabOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDestroy();
            }
        });

        adapter = new GroupAdapter();
        rvTopic = findViewById(R.id.rv_topic);
        rvTopic.setLayoutManager(new LinearLayoutManager(this));
        rvTopic.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvTopic.setAdapter(adapter);

        List<Item> topicItems = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        TopicItem.OnItemClickListener onItemClickListener = new TopicItem.OnItemClickListener() {
            @Override
            public void onImageClick() {

            }

            @Override
            public void onVideoClick(String videoUrl) {

            }

            @Override
            public void onShareClick(int topicId) {

            }
        };

        for (int i = 0; i < 20; i++) {
            calendar.set(Calendar.MINUTE, 20 - i);
            String time = new SimpleDateFormat("mm:ss", Locale.getDefault()).format(calendar.getTime());
            Topic topic = new Topic(i, "张三" + i, "今天是个好日子" + i, i % 2 == 0, time);

            topicItems.add(topic.isHasImage() ? new TopicItemWithImage(topic) : new TopicItem(topic, onItemClickListener));
        }

        List<String> images = new ArrayList<>();
        // 这应该是网络图片地址，测试写死了
        images.add(String.valueOf(R.drawable.ic_profile));
        images.add(String.valueOf(android.R.drawable.ic_menu_camera));
        images.add(String.valueOf(android.R.drawable.ic_lock_lock));
        images.add(String.valueOf(android.R.drawable.ic_menu_search));

       /* adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                if (item instanceof BannerItem) {
                    ((BannerItem) item).startBanner();
                } else if (item instanceof TopicItem) {
                    System.out.println(((TopicItem) item).topic);
                }
            }
        });*/

        BannerItem bannerItem = new BannerItem(images, this);
        Section section = new Section(bannerItem, topicItems);
        adapter.add(section);
        /*Observable<List<String>> observable = Observable.just(3)
                .flatMap((Function<Integer, ObservableSource<String>>) integer -> {
                    List<String> values = new ArrayList<>();
                    for (int i = 0; i < integer; i++) {
                        values.add("test:" + i);
                    }

                    return Observable.intervalRange(0, values.size(), 2, 2, TimeUnit.SECONDS)
                            .map(aLong -> values.get(aLong.intValue()));
                })
                .doOnNext(s -> System.out.println("单独处理：" + s))
                .toList()
                .toObservable();

        disposable = Observable.interval(0, 10, TimeUnit.SECONDS)
                .flatMap((Function<Long, ObservableSource<List<String>>>) aLong -> observable)
                .delay(2, TimeUnit.SECONDS)
                .subscribe(strings -> {
                    System.out.println("结束:");
                    for (String string : strings) {
                        System.out.println(string);
                    }
                    System.out.println("-----------------------");
                });*/

        presenter = new MainPresenterImpl();
        presenter.attachView(this);
    }

    @Override
    public void showData() {

    }

    @Override
    protected void onDestroy() {
        // presenter.onDestroy();
        disposable.dispose();
        super.onDestroy();
    }
}

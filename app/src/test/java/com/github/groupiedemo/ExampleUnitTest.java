package com.github.groupiedemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .concatMap((Function<Integer, ObservableSource<String>>) integer -> {
                    List<String> values = new ArrayList<>();
                    for (int i = 0; i < integer; i++) {
                        values.add("test:" + integer + " - " + i);
                    }

                    return Observable.fromIterable(values).delay(500, TimeUnit.MILLISECONDS);
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("结束");
                    }
                });

    }
}
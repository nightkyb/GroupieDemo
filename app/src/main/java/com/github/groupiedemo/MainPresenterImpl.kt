package com.github.groupiedemo

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * @author nightkyb created at 2019/7/18 17:10
 */
class MainPresenterImpl : MainContract.Presenter {
    private val TAG = "test"
    private var view: IBaseView? = null
    private var disposable: Disposable? = null

    override fun loadData() {
        Log.i(TAG, "loadData")
        disposable = Observable.interval(0, 2, TimeUnit.SECONDS).subscribe {
            Log.i(TAG, "test: $it")
        }
    }

    override fun attachView(view: MainContract.View) {
        view.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                onDestroy()
            }
        })
        this.view = view
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
        disposable?.dispose()
        view = null
    }
}

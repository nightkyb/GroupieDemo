package com.github.groupiedemo

interface BasePresenter<T : IBaseView> {
    fun attachView(view: T)

    fun onDestroy()
}

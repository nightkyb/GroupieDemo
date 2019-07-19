package com.github.groupiedemo

/**
 * @author nightkyb created at 2019/7/18 17:09
 */
interface MainContract {
    interface View : IBaseView {
        fun showData()
    }

    interface Presenter : BasePresenter<View> {
        fun loadData()
    }
}
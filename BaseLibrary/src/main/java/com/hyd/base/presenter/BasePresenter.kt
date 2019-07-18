package com.hyd.base.presenter

import com.hyd.base.presenter.view.BaseView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

open class BasePresenter<T:BaseView> {
    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}
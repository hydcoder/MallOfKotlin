package com.hyd.base.presenter

import com.hyd.base.presenter.view.BaseView

open class BasePresenter<T:BaseView> {
    lateinit var mView: T
}
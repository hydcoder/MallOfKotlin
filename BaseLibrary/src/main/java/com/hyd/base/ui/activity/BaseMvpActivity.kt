package com.hyd.base.ui.activity

import com.hyd.base.presenter.BasePresenter
import com.hyd.base.presenter.view.BaseView

open class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(), BaseView{

    lateinit var mPresenter: T

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

}
package com.hyd.base.ui.activity

import android.os.Bundle
import com.hyd.base.common.BaseApplication
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.base.injection.component.DaggerActivityComponent
import com.hyd.base.injection.module.ActivityModule
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.presenter.view.BaseView
import javax.inject.Inject

open class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(), BaseView{

    lateinit var activityComponent: ActivityComponent

    @Inject
    lateinit var mPresenter: T

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this)).build()
    }

}
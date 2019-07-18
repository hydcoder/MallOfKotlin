package com.hyd.base.ui.fragment

import android.os.Bundle
import com.hyd.base.common.BaseApplication
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.base.injection.component.DaggerActivityComponent
import com.hyd.base.injection.module.ActivityModule
import com.hyd.base.injection.module.LifecycleProviderModule
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.presenter.view.BaseView
import javax.inject.Inject

open abstract class BaseMvpFragment<T:BasePresenter<*>>:BaseFragment(), BaseView{

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
        injectComponent()
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().appComponent((activity?.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity!!)).lifecycleProviderModule(LifecycleProviderModule(this)).build()
    }

    abstract fun injectComponent()

}
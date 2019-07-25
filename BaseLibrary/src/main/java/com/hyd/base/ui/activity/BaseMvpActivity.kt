package com.hyd.base.ui.activity

import android.os.Bundle
import com.hyd.base.common.BaseApplication
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.base.injection.component.DaggerActivityComponent
import com.hyd.base.injection.module.ActivityModule
import com.hyd.base.injection.module.LifecycleProviderModule
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.presenter.view.BaseView
import com.hyd.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

open abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var activityComponent: ActivityComponent
    lateinit var loadingDialog: ProgressLoading

    @Inject
    lateinit var mPresenter: T

    override fun showLoading() {
        loadingDialog.showLoading()
    }

    override fun hideLoading() {
        loadingDialog.hideLoading()
    }

    override fun onError(text: String) {
        toast(text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()
    }

    private fun initActivityInjection() {
        activityComponent =
            DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this)).lifecycleProviderModule(LifecycleProviderModule(this)).build()
    }

    abstract fun injectComponent()

    override fun onResume() {
        super.onResume()
        loadingDialog = ProgressLoading.create(this)
    }

}
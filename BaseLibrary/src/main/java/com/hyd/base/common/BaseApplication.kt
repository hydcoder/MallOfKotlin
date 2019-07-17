package com.hyd.base.common

import android.app.Application
import com.hyd.base.injection.component.AppComponent
import com.hyd.base.injection.component.DaggerAppComponent
import com.hyd.base.injection.module.AppModule

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
class BaseApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initInjection()
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}
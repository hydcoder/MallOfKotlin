package com.hyd.base.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
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
        context = this

        ARouter.openLog() // 开启日志
        ARouter.openDebug() // 使用InstantRun的时候，需要打开该开关，上线之后关闭，否则有安全风险
        ARouter.printStackTrace() // 打印日志的时候打印线程堆栈

        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var context: Context
    }
}
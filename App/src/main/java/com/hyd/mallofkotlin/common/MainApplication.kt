package com.hyd.mallofkotlin.common

import cn.jpush.android.api.JPushInterface
import com.hyd.base.common.BaseApplication

/**
 * Created by hydCoder on 2019/8/19.
 * 以梦为马，明日天涯。
 */
class MainApplication: BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}
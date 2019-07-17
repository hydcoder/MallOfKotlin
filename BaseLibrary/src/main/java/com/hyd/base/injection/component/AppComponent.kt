package com.hyd.base.injection.component

import android.content.Context
import com.hyd.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context(): Context
}
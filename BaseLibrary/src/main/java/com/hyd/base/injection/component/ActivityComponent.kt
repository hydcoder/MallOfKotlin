package com.hyd.base.injection.component

import android.app.Activity
import android.content.Context
import com.hyd.base.injection.ActivityScope
import com.hyd.base.injection.module.ActivityModule
import com.hyd.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, LifecycleProviderModule::class))
interface ActivityComponent {
    fun activity(): Activity
    fun context() : Context
    fun lifecycleProvider(): LifecycleProvider<*>
}
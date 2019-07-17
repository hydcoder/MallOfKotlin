package com.hyd.base.injection.component

import android.app.Activity
import com.hyd.base.injection.ActivityScope
import com.hyd.base.injection.module.ActivityModule
import dagger.Component

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun activity(): Activity
}
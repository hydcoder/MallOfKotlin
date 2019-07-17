package com.hyd.base.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}
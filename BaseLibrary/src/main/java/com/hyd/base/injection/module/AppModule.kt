package com.hyd.base.injection.module

import android.content.Context
import com.hyd.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@Module
class AppModule(private val context: BaseApplication) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}
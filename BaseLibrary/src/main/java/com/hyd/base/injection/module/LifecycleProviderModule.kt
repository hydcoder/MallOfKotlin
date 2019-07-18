package com.hyd.base.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*> {
        return lifecycleProvider
    }
}
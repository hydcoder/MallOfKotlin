package com.hyd.goodscenter.injection.component

import com.hyd.base.injection.PerComponentScope
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.goodscenter.injection.module.CartModule
import dagger.Component

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    购物车Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(CartModule::class))
interface CartComponent {
//    fun inject(fragment: GoodsDetailTabOneFragment)
}
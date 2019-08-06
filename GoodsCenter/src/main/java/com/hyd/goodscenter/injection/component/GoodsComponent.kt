package com.hyd.goodscenter.injection.component

import com.hyd.base.injection.PerComponentScope
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.goodscenter.injection.module.CartModule
import com.hyd.goodscenter.injection.module.GoodsModule
import com.hyd.goodscenter.ui.activity.GoodsActivity
import com.hyd.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    商品Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(GoodsModule::class, CartModule::class))
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
}
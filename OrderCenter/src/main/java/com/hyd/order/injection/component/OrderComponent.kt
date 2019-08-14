package com.hyd.order.injection.component

import com.hyd.base.injection.PerComponentScope
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.order.injection.module.OrderModule
import com.hyd.order.ui.activity.OrderConfirmActivity
import com.hyd.order.ui.fragment.OrderFragment
import dagger.Component

/**
 * Created by hydCoder on 2019/8/7.
 * 以梦为马，明日天涯。
 */
/*
    订单Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(OrderModule::class))
interface OrderComponent {
    fun inject(activity: OrderConfirmActivity)
    fun inject(fragment: OrderFragment)
//    fun inject(activity:OrderDetailActivity)
}
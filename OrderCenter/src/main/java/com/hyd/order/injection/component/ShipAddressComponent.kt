package com.hyd.order.injection.component

import com.hyd.base.injection.PerComponentScope
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.order.injection.module.ShipAddressModule
import com.hyd.order.ui.activity.EditShipAddressActivity
import com.hyd.order.ui.activity.ShipAddressActivity
import dagger.Component

/**
 * Created by hydCoder on 2019/8/7.
 * 以梦为马，明日天涯。
 */
/*
    收货地址Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(ShipAddressModule::class))
interface ShipAddressComponent {
    fun inject(activity: ShipAddressActivity)
    fun inject(activity: EditShipAddressActivity)
}
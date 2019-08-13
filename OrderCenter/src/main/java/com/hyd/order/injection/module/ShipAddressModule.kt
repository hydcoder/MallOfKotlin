package com.hyd.order.injection.module

import com.hyd.order.service.ShipAddressService
import com.hyd.order.service.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/8/7.
 * 以梦为马，明日天涯。
 */
/*
   收获地址Module
 */
@Module
class ShipAddressModule {

    @Provides
    fun provideShipAddressService(shipAddressService: ShipAddressServiceImpl): ShipAddressService {
        return shipAddressService
    }

}
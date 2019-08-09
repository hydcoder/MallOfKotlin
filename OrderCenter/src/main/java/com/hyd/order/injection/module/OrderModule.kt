package com.hyd.order.injection.module

import com.hyd.order.service.OrderService
import com.hyd.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/8/7.
 * 以梦为马，明日天涯。
 */
/*
    订单Module
 */
@Module
class OrderModule {

    @Provides
    fun provideOrderService(orderService: OrderServiceImpl): OrderService {
        return orderService
    }

}
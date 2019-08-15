package com.hyd.pay.injection.module

import com.hyd.pay.service.PayService
import com.hyd.pay.service.impl.PayServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/8/15.
 * 以梦为马，明日天涯。
 */
/*
    支付 Module
 */
@Module
class PayModule {

    @Provides
    fun providePayService(payService: PayServiceImpl): PayService {
        return payService
    }

}
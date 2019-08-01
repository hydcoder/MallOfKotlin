package com.hyd.goodscenter.injection.module

import com.hyd.goodscenter.presenter.CartService
import com.hyd.goodscenter.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    购物车Module
 */
@Module
class CartModule {

    @Provides
    fun provideCartService(cartService: CartServiceImpl): CartService {
        return cartService
    }

}
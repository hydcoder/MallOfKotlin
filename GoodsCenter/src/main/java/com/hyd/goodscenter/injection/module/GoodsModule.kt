package com.hyd.goodscenter.injection.module

import com.hyd.goodscenter.service.GoodsService
import com.hyd.goodscenter.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    商品Module
 */
@Module
class GoodsModule {

    @Provides
    fun provideGoodsService(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }

}
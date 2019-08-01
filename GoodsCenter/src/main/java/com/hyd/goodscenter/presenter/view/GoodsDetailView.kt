package com.hyd.goodscenter.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.goodscenter.data.protocal.Goods

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
/*
    商品详情 视图回调
 */
interface GoodsDetailView : BaseView {

    //获取商品详情
    fun onGetGoodsDetailResult(result: Goods)

    //加入购物车
    fun onAddCartResult(result: Int)
}

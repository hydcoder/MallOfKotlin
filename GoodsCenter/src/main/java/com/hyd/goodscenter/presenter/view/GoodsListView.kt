package com.hyd.goodscenter.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.goodscenter.data.protocal.Goods

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    商品列表 视图回调
 */
interface GoodsListView : BaseView {

    //获取商品列表
    fun onGetGoodsListResult(result: MutableList<Goods>?)
}
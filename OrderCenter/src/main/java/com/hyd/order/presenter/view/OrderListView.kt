package com.hyd.order.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.order.data.protocol.Order

/**
 * Created by hydCoder on 2019/8/14.
 * 以梦为马，明日天涯。
 */
/*
    订单列表 视图回调
 */
interface OrderListView : BaseView {

    //获取订单列表回调
    fun onGetOrderListResult(result: MutableList<Order>?)

    //确认订单回调
    fun onConfirmOrderResult(result: Boolean)

    //取消订单回调
    fun onCancelOrderResult(result: Boolean)

}
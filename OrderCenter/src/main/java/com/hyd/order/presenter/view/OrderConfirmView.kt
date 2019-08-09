package com.hyd.order.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.order.data.protocol.Order

/**
 * Created by hydCoder on 2019/8/7.
 * 以梦为马，明日天涯。
 */
/*
    订单确认页 视图回调
 */
interface OrderConfirmView : BaseView {

    //获取订单回调
    fun onGetOrderByIdResult(result: Order)

    //提交订单回调
    fun onSubmitOrderResult(result: Boolean)
}
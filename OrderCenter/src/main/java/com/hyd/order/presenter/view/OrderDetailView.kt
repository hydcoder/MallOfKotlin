package com.hyd.order.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.order.data.protocol.Order

/**
 * Created by hydCoder on 2019/8/15.
 * 以梦为马，明日天涯。
 */
/*
    订单详情页 视图回调
 */
interface OrderDetailView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}
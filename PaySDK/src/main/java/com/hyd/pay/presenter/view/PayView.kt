package com.hyd.pay.presenter.view

import com.hyd.base.presenter.view.BaseView

/**
 * Created by hydCoder on 2019/8/15.
 * 以梦为马，明日天涯。
 */
/*
    支付 视图回调
 */
interface PayView : BaseView {

    //获取支付签名
    fun onGetSignResult(result: String)

    //同步支付成功状态
    fun onPayOrderResult(result: Boolean)

}
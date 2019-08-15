package com.hyd.pay.data.protocal

/**
 * Created by hydCoder on 2019/8/15.
 * 以梦为马，明日天涯。
 */
/*
    获取支付宝 支付签名
 */
data class GetPaySignReq(val orderId: Int, val totalPrice: Long)
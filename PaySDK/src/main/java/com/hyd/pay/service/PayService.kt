package com.hyd.pay.service

import rx.Observable

/**
 * Created by hydCoder on 2019/8/15.
 * 以梦为马，明日天涯。
 */
/*
    支付 业务接口
 */
interface PayService {

    /*
    获取支付宝支付签名
 */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>

    /*
    刷新订单状态已支付
 */
    fun payOrder(orderId: Int): Observable<Boolean>
}

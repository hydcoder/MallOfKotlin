package com.hyd.pay.data.repository

import com.hyd.base.data.net.RetrofitFactory
import com.hyd.base.data.protocal.BaseResp
import com.hyd.pay.data.api.PayApi
import com.hyd.pay.data.protocal.GetPaySignReq
import com.hyd.pay.data.protocal.PayOrderReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/8/15.
 * 以梦为马，明日天涯。
 */
/*
   支付数据层
 */
class PayRepository @Inject constructor() {

    /*
        获取支付宝支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).getPaySign(GetPaySignReq(orderId, totalPrice))
    }

    /*
        刷新订单状态已支付
     */
    fun payOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).payOrder(PayOrderReq(orderId))
    }

}
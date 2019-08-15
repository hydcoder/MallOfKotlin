package com.hyd.pay.service.impl

import com.hyd.base.ext.convert
import com.hyd.base.ext.convertBoolean
import com.hyd.pay.data.repository.PayRepository
import com.hyd.pay.service.PayService
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/8/15.
 * 以梦为马，明日天涯。
 */
/*
    支付 业务实现类
 */
class PayServiceImpl @Inject constructor(): PayService {

    @Inject
    lateinit var repository: PayRepository

    /*
        获取支付签名
     */
    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String> {
        return repository.getPaySign(orderId,totalPrice).convert()
    }

    /*
        支付订单，同步订单状态
     */
    override fun payOrder(orderId: Int): Observable<Boolean> {
        return repository.payOrder(orderId).convertBoolean()
    }
}
package com.hyd.order.data.protocol

import com.kotlin.order.data.protocol.ShipAddress

/*
    订单数据类
 */
data class Order(
        val id: Int,
        val payType: Int,
        var shipAddress: ShipAddress?,
        val totalPrice: Long,
        var orderStatus: Int,
        val orderGoodsList: MutableList<OrderGoods>
)


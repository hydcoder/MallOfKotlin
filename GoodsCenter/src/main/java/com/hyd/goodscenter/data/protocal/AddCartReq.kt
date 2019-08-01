package com.hyd.goodscenter.data.protocal

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
/*
    添加商品到购物车请求
 */
data class AddCartReq(
    val goodsId: Int, //商品ID
    val goodsDesc: String, //商品描述
    val goodsIcon: String, //商品图标
    val goodsPrice: Long, //商品价格
    val goodsCount: Int, //商品数量
    val goodsSku: String //商品SKU
)
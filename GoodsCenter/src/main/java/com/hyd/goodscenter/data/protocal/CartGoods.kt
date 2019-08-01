package com.hyd.goodscenter.data.protocal

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
/*
    购物车商品数据类
 */
data class CartGoods(
    val id: Int,//购物车单项商品ID
    val goodsId:Int,//具体商品ID
    val goodsDesc: String,//商品描述
    val goodsIcon: String,//商品图片
    val goodsPrice: Long,//商品价格
    var goodsCount: Int,//商品数量
    val goodsSku:String,//商品SKU
    var isSelected:Boolean//是否选中
)
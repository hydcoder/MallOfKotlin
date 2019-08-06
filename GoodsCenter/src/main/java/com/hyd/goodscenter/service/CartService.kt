package com.hyd.goodscenter.service

import com.hyd.goodscenter.data.protocal.CartGoods
import rx.Observable

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
/*
    购物车 业务 接口
 */
interface CartService {
    /*
        添加商品到购物车
     */
    fun addCart(
        goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
        goodsCount: Int, goodsSku: String
    ): Observable<Int>

    /*
        获取购物车列表
     */
    fun getCartList(): Observable<MutableList<CartGoods>?>

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<Boolean>

    /*
        购物车结算
    */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int>
}
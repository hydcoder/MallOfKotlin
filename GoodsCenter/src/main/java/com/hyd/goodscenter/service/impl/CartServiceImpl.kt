package com.hyd.goodscenter.service.impl

import com.hyd.base.ext.convert
import com.hyd.base.ext.convertBoolean
import com.hyd.goodscenter.data.protocal.CartGoods
import com.hyd.goodscenter.data.repository.CartRepository
import com.hyd.goodscenter.presenter.CartService
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
/*
    购物车 业务层实现类
 */
class CartServiceImpl @Inject constructor(): CartService {

    @Inject
    lateinit var repository: CartRepository

    /*
        加入购物车
     */
    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int> {
        return repository.addCart(goodsId,goodsDesc,goodsIcon,goodsPrice,
            goodsCount,goodsSku).convert()
    }

    /*
        获取购物车列表
     */
    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return repository.getCartList().convert()
    }

    /*
        删除购物车商品
     */
    override fun deleteCartList(list: List<Int>): Observable<Boolean> {
        return repository.deleteCartList(list).convertBoolean()
    }

    /*
        提交购物车商品
     */
    override fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int> {
        return repository.submitCart(list,totalPrice).convert()
    }
}
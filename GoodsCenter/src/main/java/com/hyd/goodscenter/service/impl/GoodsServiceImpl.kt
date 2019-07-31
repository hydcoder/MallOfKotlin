package com.hyd.goodscenter.service.impl

import com.hyd.base.ext.convert
import com.hyd.goodscenter.data.protocal.Goods
import com.hyd.goodscenter.data.repository.GoodsRepository
import com.hyd.goodscenter.service.GoodsService
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    商品 业务层 实现类
 */
class GoodsServiceImpl @Inject constructor(): GoodsService {

    @Inject
    lateinit var repository: GoodsRepository

    /*
        获取商品列表
     */
    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsList(categoryId,pageNo).convert()
    }

    /*
        根据关键字查询商品
     */
    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsListByKeyword(keyword,pageNo).convert()
    }

    /*
        获取商品详情
     */
    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
        return repository.getGoodsDetail(goodsId).convert()
    }
}
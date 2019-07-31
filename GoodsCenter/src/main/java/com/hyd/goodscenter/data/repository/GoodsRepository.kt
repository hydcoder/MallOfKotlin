package com.hyd.goodscenter.data.repository

import com.hyd.base.data.net.RetrofitFactory
import com.hyd.base.data.protocal.BaseResp
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    商品数据层
 */
class GoodsRepository @Inject constructor() {

    /*
        根据分类搜索商品
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResp<MutableList<com.hyd.goodscenter.data.protocal.Goods>?>> {
        return RetrofitFactory.instance.create(com.hyd.goodscenter.data.api.GoodsApi::class.java).getGoodsList(
            com.hyd.goodscenter.data.protocal.GetGoodsListReq(
                categoryId,
                pageNo
            )
        )
    }

    /*
        根据关键字搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<BaseResp<MutableList<com.hyd.goodscenter.data.protocal.Goods>?>> {
        return RetrofitFactory.instance.create(com.hyd.goodscenter.data.api.GoodsApi::class.java).getGoodsListByKeyword(
            com.hyd.goodscenter.data.protocal.GetGoodsListByKeywordReq(keyword, pageNo)
        )
    }

    /*
        商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<BaseResp<com.hyd.goodscenter.data.protocal.Goods>> {
        return RetrofitFactory.instance.create(com.hyd.goodscenter.data.api.GoodsApi::class.java).getGoodsDetail(
            com.hyd.goodscenter.data.protocal.GetGoodsDetailReq(
                goodsId
            )
        )
    }
}
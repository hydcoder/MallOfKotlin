package com.hyd.goodscenter.data.api

import com.hyd.base.data.protocal.BaseResp
import com.hyd.goodscenter.data.protocal.GetGoodsDetailReq
import com.hyd.goodscenter.data.protocal.GetGoodsListByKeywordReq
import com.hyd.goodscenter.data.protocal.GetGoodsListReq
import com.hyd.goodscenter.data.protocal.Goods
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    商品接口
 */
interface GoodsApi {
    /*
        获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResp<Goods>>
}
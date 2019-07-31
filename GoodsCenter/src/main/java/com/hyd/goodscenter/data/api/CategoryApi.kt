package com.hyd.goodscenter.data.api

import com.hyd.base.data.protocal.BaseResp
import com.hyd.goodscenter.data.protocal.Category
import com.hyd.goodscenter.data.protocal.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
interface CategoryApi {
    /*
        获取商品分类列表
     */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}
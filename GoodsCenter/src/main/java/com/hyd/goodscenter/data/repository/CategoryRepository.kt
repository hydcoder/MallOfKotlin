package com.hyd.goodscenter.data.repository

import com.hyd.base.data.net.RetrofitFactory
import com.hyd.base.data.protocal.BaseResp
import com.hyd.goodscenter.data.api.CategoryApi
import com.hyd.goodscenter.data.protocal.Category
import com.hyd.goodscenter.data.protocal.GetCategoryReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
class CategoryRepository @Inject constructor(){

    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(
            GetCategoryReq(parentId)
        )
    }
}
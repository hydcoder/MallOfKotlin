package com.hyd.goodscenter.service

import com.hyd.goodscenter.data.protocal.Category
import rx.Observable

/**
 * Author: Created by HYD on 2019/7/16.
 * 以梦为马，明日天涯。
 */
open interface CategoryService {
    fun getCategory(parentId: Int): Observable<MutableList<Category>?>
}
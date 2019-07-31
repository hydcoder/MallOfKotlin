package com.hyd.goodscenter.service.impl

import com.hyd.base.ext.convert
import com.hyd.goodscenter.data.protocal.Category
import com.hyd.goodscenter.data.repository.CategoryRepository
import com.hyd.goodscenter.service.CategoryService
import rx.Observable
import javax.inject.Inject

/**
 * Author: Created by HYD on 2019/7/16.
 * 以梦为马，明日天涯。
 */
class CategoryServiceImpl @Inject constructor(): CategoryService {

    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return repository.getCategory(parentId).convert()
    }
}
package com.hyd.goodscenter.injection.module

import com.hyd.goodscenter.service.CategoryService
import com.hyd.goodscenter.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@Module
class CategoryModule {

    @Provides
    fun providesCategoryService(categoryService: CategoryServiceImpl): CategoryService {
        return categoryService
    }
}
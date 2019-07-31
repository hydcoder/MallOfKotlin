package com.hyd.goodscenter.injection.component

import com.hyd.base.injection.PerComponentScope
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.goodscenter.injection.module.CategoryModule
import com.hyd.goodscenter.ui.fragment.CategoryFragment
import dagger.Component

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}
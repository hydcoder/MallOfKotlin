package com.hyd.goodscenter.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.goodscenter.data.protocal.Category

interface CategoryView: BaseView {
    fun onGetCategoryResult(result: MutableList<Category>?)
}
package com.hyd.goodscenter.presenter

import com.hyd.base.ext.execute
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.rx.BaseSubscriber
import com.hyd.goodscenter.data.protocal.Category
import com.hyd.goodscenter.presenter.view.CategoryView
import com.hyd.goodscenter.service.CategoryService
import javax.inject.Inject

class CategoryPresenter @Inject constructor(): BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    fun getCategory(parentId: Int){

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()

        categoryService.getCategory(parentId)
            .execute(object : BaseSubscriber<MutableList<Category>?>(mView){
                override fun onNext(t: MutableList<Category>?) {
                    mView.onGetCategoryResult(t)
                }
            }, lifecycleProvider)
    }
}
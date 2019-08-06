package com.hyd.goodscenter.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.goodscenter.data.protocal.CartGoods

interface CartListView: BaseView {
    fun onGetCartListResult(result: MutableList<CartGoods>?)
    fun onDeleteCartListResult(result: Boolean)
    fun onSubmitCartListResult(result: Int)
}
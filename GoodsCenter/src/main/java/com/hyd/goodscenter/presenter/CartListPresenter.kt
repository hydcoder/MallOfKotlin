package com.hyd.goodscenter.presenter

import com.hyd.base.ext.execute
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.rx.BaseSubscriber
import com.hyd.goodscenter.data.protocal.CartGoods
import com.hyd.goodscenter.presenter.view.CartListView
import com.hyd.goodscenter.service.CartService
import javax.inject.Inject

class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {

    @Inject
    lateinit var cartService: CartService

    fun getCartList() {

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()

        cartService.getCartList()
            .execute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {
                override fun onNext(t: MutableList<CartGoods>?) {
                    mView.onGetCartListResult(t)
                }
            }, lifecycleProvider)
    }

    fun deleteCartList(list: List<Int>) {

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()

        cartService.deleteCartList(list)
            .execute(object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    mView.onDeleteCartListResult(t)
                }
            }, lifecycleProvider)
    }

    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long) {

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()

        cartService.submitCart(list, totalPrice)
            .execute(object : BaseSubscriber<Int>(mView) {
                override fun onNext(t: Int) {
                    mView.onSubmitCartListResult(t)
                }
            }, lifecycleProvider)
    }
}
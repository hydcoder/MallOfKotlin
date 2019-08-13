package com.hyd.order.presenter

import com.hyd.base.ext.execute
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.rx.BaseSubscriber
import com.hyd.order.data.protocol.ShipAddress
import com.hyd.order.presenter.view.EditShipAddressView
import com.hyd.order.service.ShipAddressService
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/8/13.
 * 以梦为马，明日天涯。
 */
/*
    编辑收货人信息Presenter
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService


    /*
        添加收货人信息
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName,shipUserMobile,shipAddress).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onAddShipAddressResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        修改收货人信息
     */
    fun editShipAddress(address: ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onEditShipAddressResult(t)
            }
        }, lifecycleProvider)

    }


}
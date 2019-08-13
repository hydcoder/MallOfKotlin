package com.hyd.order.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.order.data.protocol.ShipAddress

/**
 * Created by hydCoder on 2019/8/13.
 * 以梦为马，明日天涯。
 */
/*
    收货人列表 视图回调
 */
interface ShipAddressView : BaseView {

    //获取收货人列表回调
    fun onGetShipAddressResult(result: MutableList<ShipAddress>?)
    //设置默认收货人回调
    fun onSetDefaultResult(result: Boolean)
    //删除收货人回调
    fun onDeleteResult(result: Boolean)

}
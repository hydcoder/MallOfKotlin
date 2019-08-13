package com.hyd.order.presenter.view

import com.hyd.base.presenter.view.BaseView

/**
 * Created by hydCoder on 2019/8/13.
 * 以梦为马，明日天涯。
 */
/*
    编辑收货人信息 视图回调
 */
interface EditShipAddressView : BaseView {
    //添加收货人回调
    fun onAddShipAddressResult(result: Boolean)
    //修改收货人回调
    fun onEditShipAddressResult(result: Boolean)
}
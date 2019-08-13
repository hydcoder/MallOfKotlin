package com.hyd.order.ui.activity

import android.os.Bundle
import com.hyd.base.ext.onClick
import com.hyd.base.ui.activity.BaseMvpActivity
import com.hyd.order.R
import com.hyd.order.common.OrderConstant
import com.hyd.order.data.protocol.ShipAddress
import com.hyd.order.injection.component.DaggerShipAddressComponent
import com.hyd.order.injection.module.ShipAddressModule
import com.hyd.order.presenter.EditShipAddressPresenter
import com.hyd.order.presenter.view.EditShipAddressView
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 * Created by hydCoder on 2019/8/13.
 * 以梦为马，明日天涯。
 */
class EditShipAddressActivity : BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {

    private var mAddress: ShipAddress? = null

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent).shipAddressModule(ShipAddressModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initView()
        initData()
    }

    private fun initView() {
        mSaveBtn.onClick {
            if (mShipNameEt.text.isNullOrEmpty()) {
                toast("请输入收货人")
                return@onClick
            }
            if (mShipMobileEt.text.isNullOrEmpty()) {
                toast("请输入联系方式")
                return@onClick
            }
            if (mShipAddressEt.text.isNullOrEmpty()) {
                toast("请输入详细地址")
                return@onClick
            }

            if (mAddress == null) {
                mPresenter.addShipAddress(
                    mShipNameEt.text.toString(),
                    mShipMobileEt.text.toString(),
                    mShipAddressEt.text.toString()
                )
            } else {
                mAddress!!.shipUserName = mShipNameEt.text.toString()
                mAddress!!.shipUserMobile = mShipMobileEt.text.toString()
                mAddress!!.shipAddress = mShipAddressEt.text.toString()
                mPresenter.editShipAddress(mAddress!!)
            }
        }
    }

    private fun initData() {
        mAddress = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        mAddress?.let {
            mShipNameEt.setText(it.shipUserName)
            mShipMobileEt.setText(it.shipUserMobile)
            mShipAddressEt.setText(it.shipAddress)
        }
    }

    override fun onAddShipAddressResult(result: Boolean) {
        toast("添加成功")
        finish()
    }

    override fun onEditShipAddressResult(result: Boolean) {
        toast("编辑成功")
        finish()
    }
}
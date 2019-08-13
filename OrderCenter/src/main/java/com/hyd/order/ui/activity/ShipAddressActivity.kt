package com.hyd.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.hyd.base.ext.onClick
import com.hyd.base.ext.startLoading
import com.hyd.base.ui.activity.BaseMvpActivity
import com.hyd.order.R
import com.hyd.order.common.OrderConstant
import com.hyd.order.data.protocol.ShipAddress
import com.hyd.order.injection.component.DaggerShipAddressComponent
import com.hyd.order.injection.module.ShipAddressModule
import com.hyd.order.presenter.ShipAddressPresenter
import com.hyd.order.presenter.view.ShipAddressView
import com.hyd.order.ui.adapter.ShipAddressAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by hydCoder on 2019/8/13.
 * 以梦为马，明日天涯。
 */
class ShipAddressActivity: BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {

    private lateinit var mAdapter: ShipAddressAdapter

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent).shipAddressModule(ShipAddressModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initView() {
        mAddressRv.layoutManager = LinearLayoutManager(this)
        mAdapter = ShipAddressAdapter(this)
        mAddressRv.adapter = mAdapter

        mAdapter.mOptClickListener = object : ShipAddressAdapter.OnOptClickListener {
            override fun onSetDefault(address: ShipAddress) {
                mPresenter.setDefaultShipAddress(address)
            }

            override fun onEdit(address: ShipAddress) {
                startActivity<EditShipAddressActivity>(OrderConstant.KEY_SHIP_ADDRESS to address)
            }

            override fun onDelete(address: ShipAddress) {
                AlertView("删除", "确定删除该地址吗？", "取消", null, arrayOf("确定"), this@ShipAddressActivity,
                    AlertView.Style.Alert, OnItemClickListener { _, position ->
                        if (position == 0) {
                            mPresenter.deleteShipAddress(address.id)
                        }
                    }).show()
            }

        }

        mAddAddressBtn.onClick {
            startActivity<EditShipAddressActivity>()
        }
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()
    }

    override fun onGetShipAddressResult(result: MutableList<ShipAddress>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onSetDefaultResult(result: Boolean) {
        if (result) {
            toast("设置默认成功")
        } else {
            toast("设置默认失败")
        }
        loadData()
    }

    override fun onDeleteResult(result: Boolean) {
        if (result) {
            toast("删除成功")
        } else {
            toast("删除失败")
        }
        loadData()
    }
}
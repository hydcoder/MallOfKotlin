package com.hyd.order.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.hyd.base.ext.onClick
import com.hyd.base.ext.setVisible
import com.hyd.base.ui.activity.BaseMvpActivity
import com.hyd.order.R
import com.hyd.order.data.protocol.Order
import com.hyd.order.event.SelectAddressEvent
import com.hyd.order.injection.component.DaggerOrderComponent
import com.hyd.order.injection.module.OrderModule
import com.hyd.order.presenter.OrderConfirmPresenter
import com.hyd.order.presenter.view.OrderConfirmView
import com.hyd.order.ui.adapter.OrderGoodsAdapter
import com.hyd.provider.common.ProviderConstant
import com.hyd.provider.router.RouterPath
import com.kotlin.base.utils.YuanFenConverter
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by hydCoder on 2019/8/9.
 * 以梦为马，明日天涯。
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0

    private lateinit var mAdapter: OrderGoodsAdapter

    private lateinit var mOrder: Order

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent).orderModule(OrderModule()).build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
        ARouter.getInstance().inject(this)

        initView()
        initObserve()
        loadData()
    }

    private fun initView() {
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)

        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }

        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }

        mSubmitOrderBtn.onClick {
            mPresenter.submitOrder(mOrder)
        }
    }

    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
            .subscribe {
                mOrder.shipAddress = it.address
                updateAddressView()
            }
            .registerInBus(this)
    }

    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    @SuppressLint("SetTextI18n")
    private fun updateAddressView() {
        mOrder?.let {
            if (it.shipAddress == null) {
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            } else {
                mSelectShipTv.setVisible(false)
                mShipView.setVisible(true)

                mShipNameTv.text = "${it.shipAddress!!.shipUserName}     ${it.shipAddress!!.shipUserMobile}"
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }
        }
    }

    override fun onGetOrderByIdResult(result: Order) {
        mOrder = result
        updateAddressView()
        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.totalPrice)
        mAdapter.setData(result.orderGoodsList)
    }

    override fun onSubmitOrderResult(result: Boolean) {
        if (result) {
            toast("订单提交成功")
            ARouter.getInstance().build(RouterPath.PaySDK.PATH_PAY)
                .withInt(ProviderConstant.KEY_ORDER_ID, mOrder.id)
                .withLong(ProviderConstant.KEY_ORDER_PRICE, mOrder.totalPrice)
                .navigation()
            finish()
        } else {
            toast("订单提交失败")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
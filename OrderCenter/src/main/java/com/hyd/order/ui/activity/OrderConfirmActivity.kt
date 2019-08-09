package com.hyd.order.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hyd.base.ui.activity.BaseMvpActivity
import com.hyd.order.R
import com.hyd.order.data.protocol.Order
import com.hyd.order.injection.component.DaggerOrderComponent
import com.hyd.order.injection.module.OrderModule
import com.hyd.order.presenter.OrderConfirmPresenter
import com.hyd.order.presenter.view.OrderConfirmView
import com.hyd.provider.common.ProviderConstant
import com.hyd.provider.router.RouterPath
import com.kotlin.base.utils.YuanFenConverter
import kotlinx.android.synthetic.main.activity_order_confirm.*

/**
 * Created by hydCoder on 2019/8/9.
 * 以梦为马，明日天涯。
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity: BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent).orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
        ARouter.getInstance().inject(this)

        loadData()
    }

    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    override fun onGetOrderByIdResult(result: Order) {
        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.totalPrice)
    }

    override fun onSubmitOrderResult(result: Boolean) {
    }
}
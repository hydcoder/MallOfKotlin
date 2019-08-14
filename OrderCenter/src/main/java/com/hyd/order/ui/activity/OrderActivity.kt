package com.hyd.order.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.hyd.base.ui.activity.BaseActivity
import com.hyd.order.R
import com.hyd.order.common.OrderConstant
import com.hyd.order.common.OrderStatus
import com.hyd.order.ui.adapter.OrderVpAdapter
import kotlinx.android.synthetic.main.activity_order.*

/**
 * Created by hydCoder on 2019/8/14.
 * 以梦为马，明日天涯。
 */
class OrderActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initView()
    }

    private fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_FIXED
        mOrderVp.adapter = OrderVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        mOrderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)
    }
}
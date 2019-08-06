package com.hyd.goodscenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.hyd.base.ext.onClick
import com.hyd.base.ui.activity.BaseActivity
import com.hyd.base.utils.AppPrefsUtils
import com.hyd.goodscenter.R
import com.hyd.goodscenter.common.GoodsConstant
import com.hyd.goodscenter.event.AddCartEvent
import com.hyd.goodscenter.event.UpdateCartSizeEvent
import com.hyd.goodscenter.ui.adapter.GoodsDetailVpAdapter
import com.hyd.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
class GoodsDetailActivity: BaseActivity() {

    private lateinit var mCartBadge: QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initObserve()
        loadCartSize()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        //TabLayout关联ViewPager
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }

        mLeftIv.onClick {
            finish()
        }

        mCartBadge = QBadgeView(this)
    }

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
            .subscribe {
                setCartBadge()
            }.registerInBus(this)
    }

    private fun setCartBadge() {
        mCartBadge.badgeGravity = Gravity.TOP or Gravity.END
        mCartBadge.setGravityOffset(22f, -2f, true)
        mCartBadge.setBadgeTextSize(10f, true)
        mCartBadge.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }

    private fun loadCartSize() {
        setCartBadge()
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
package com.hyd.goodscenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.hyd.base.ui.activity.BaseActivity
import com.hyd.goodscenter.R
import com.hyd.goodscenter.ui.adapter.GoodsDetailVpAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
class GoodsDetailActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        //TabLayout关联ViewPager
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)
    }
}
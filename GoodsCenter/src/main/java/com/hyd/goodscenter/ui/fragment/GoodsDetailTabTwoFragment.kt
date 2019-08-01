package com.hyd.goodscenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.hyd.base.ext.loadImage
import com.hyd.base.ui.fragment.BaseFragment
import com.hyd.goodscenter.R
import com.hyd.goodscenter.event.GoodsDetailImageEvent
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
class GoodsDetailTabTwoFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_goods_detail_tab_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    /*
       初始化监听，商品详情获取成功后，加载当前页面
    */
    private fun initObserve() {
        Bus.observe<GoodsDetailImageEvent>().subscribe {
            run {
                mGoodsDetailOneIv.loadImage(it.imgOne)
                mGoodsDetailTwoIv.loadImage(it.imgTwo)
            }
        }.registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
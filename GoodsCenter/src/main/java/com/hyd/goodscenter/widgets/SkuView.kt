package com.hyd.goodscenter.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.eightbitlab.rxbus.Bus
import com.hyd.goodscenter.R
import com.hyd.goodscenter.common.GoodsConstant
import com.hyd.goodscenter.data.protocal.GoodsSku
import com.hyd.goodscenter.event.SkuChangedEvent
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.layout_sku_view.view.*

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
/*
    单个SKU
 */
class SkuView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    FrameLayout(context, attrs, defStyle) {
    private lateinit var mGoodsSku: GoodsSku

    init {
        View.inflate(context, R.layout.layout_sku_view, this)
    }

    /*
        动态设置SKU数据
     */
    fun setSkuData(goodsSku: GoodsSku) {
        mGoodsSku = goodsSku
        mSkuTitleTv.text = goodsSku.skuTitle

        //FlowLayout设置数据
        mSkuContentView.adapter = object : TagAdapter<String>(goodsSku.skuContent) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.layout_sku_item, parent, false) as TextView
                view.text = t
                return view
            }
        }

        mSkuContentView.adapter.setSelectedList(0)

        mSkuContentView.setOnTagClickListener { _, _, _ ->
            Bus.send(SkuChangedEvent())
            true
        }
    }

    /*
        获取选中的SKU
     */
    fun getSkuInfo(): String {
        return mSkuTitleTv.text.toString() + GoodsConstant.SKU_SEPARATOR +
                mGoodsSku.skuContent[mSkuContentView.selectedList.first()]
    }
}
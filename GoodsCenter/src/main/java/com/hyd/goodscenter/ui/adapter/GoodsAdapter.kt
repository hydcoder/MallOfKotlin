package com.hyd.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyd.base.ext.loadImage
import com.hyd.goodscenter.R
import com.hyd.goodscenter.data.protocal.Goods
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.YuanFenConverter
import kotlinx.android.synthetic.main.layout_goods_item.view.*

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    商品数据适配器
 */
class GoodsAdapter(context: Context) : BaseRecyclerViewAdapter<Goods, GoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(
                R.layout.layout_goods_item,
                parent,
                false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //商品图标
        holder.itemView.mGoodsIconIv.loadImage(model.goodsDefaultIcon)
        //商品描述
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        //商品价格
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsDefaultPrice)
        //商品销量及库存
        holder.itemView.mGoodsSalesStockTv.text = "销量${model.goodsSalesCount}件     库存${model.goodsStockCount}"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
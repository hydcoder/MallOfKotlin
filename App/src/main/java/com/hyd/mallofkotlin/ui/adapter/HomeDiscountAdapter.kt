package com.hyd.mallofkotlin.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyd.base.ext.loadIamge
import com.hyd.mallofkotlin.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 * Created by hydCoder on 2019/7/30.
 * 以梦为马，明日天涯。
 */
class HomeDiscountAdapter(mContext: Context) :
    BaseRecyclerViewAdapter<String, HomeDiscountAdapter.ViewHolder>(mContext) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_discount_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.mGoodsIconIv.loadIamge(dataList.get(position))

        // 假数据
        holder.itemView.mDiscountAfterTv.text = "￥99.80"
        holder.itemView.mDiscountBeforeTv.text = "￥299.00"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.mDiscountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            itemView.mDiscountBeforeTv.paint.isAntiAlias = true
        }
    }
}
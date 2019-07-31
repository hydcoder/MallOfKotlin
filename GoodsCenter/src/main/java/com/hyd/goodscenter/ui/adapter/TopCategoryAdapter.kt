package com.hyd.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyd.goodscenter.R
import com.hyd.goodscenter.data.protocal.Category
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    一级商品分类Adapter
 */
class TopCategoryAdapter(context: Context): BaseRecyclerViewAdapter<Category, TopCategoryAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_top_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val category = dataList[position]
        holder.itemView.mTopCategoryNameTv.text = category.categoryName
        holder.itemView.mTopCategoryNameTv.isSelected = category.isSelected
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}
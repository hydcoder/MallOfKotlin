package com.hyd.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyd.base.ext.loadImage
import com.hyd.goodscenter.R
import com.hyd.goodscenter.data.protocal.Category
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    二级商品分类Adapter
 */
class SecondCategoryAdapter(context: Context) :
    BaseRecyclerViewAdapter<Category, SecondCategoryAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.layout_second_category_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val category = dataList[position]
        //分类图片
        holder.itemView.mCategoryIconIv.loadImage(category.categoryIcon)
        //分类名称
        holder.itemView.mSecondCategoryNameTv.text = category.categoryName

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
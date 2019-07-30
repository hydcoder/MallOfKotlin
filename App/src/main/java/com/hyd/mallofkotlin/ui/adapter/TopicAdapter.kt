package com.hyd.mallofkotlin.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyd.base.ext.loadIamge
import com.hyd.mallofkotlin.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 * Created by hydCoder on 2019/7/30.
 * 以梦为马，明日天涯。
 */
class TopicAdapter(private val context: Context, private val list: List<String>): PagerAdapter() {

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(paramView: View, paramObject: Any): Boolean {
        return paramView == paramObject
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rootView = LayoutInflater.from(context).inflate(R.layout.layout_topic_item, null)
        rootView.mTopicIv.loadIamge(list[position])
        container.addView(rootView)
        return rootView
    }
}
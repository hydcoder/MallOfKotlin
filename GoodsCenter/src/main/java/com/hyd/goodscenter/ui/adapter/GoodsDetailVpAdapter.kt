package com.hyd.goodscenter.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hyd.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import com.hyd.goodscenter.ui.fragment.GoodsDetailTabTwoFragment

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
class GoodsDetailVpAdapter(fm: FragmentManager, context: Context): FragmentPagerAdapter(fm) {

    private val titles = arrayOf("商品", "详情")

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return GoodsDetailTabOneFragment()
        } else {
            return GoodsDetailTabTwoFragment()
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}
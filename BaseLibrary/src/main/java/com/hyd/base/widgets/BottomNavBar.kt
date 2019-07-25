package com.hyd.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.hyd.base.R

/**
 * Created by hydCoder on 2019/7/25.
 * 以梦为马，明日天涯。
 */
class BottomNavBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    private lateinit var mCartBadgeItem: TextBadgeItem
    private lateinit var mMsgBadgeItem: ShapeBadgeItem

    init {
        // 首页
        val homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press, resources.getString(R.string.nav_bar_home))
            .setInactiveIconResource(R.drawable.btn_nav_home_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)

        // 分类
        val categoryItem = BottomNavigationItem(R.drawable.btn_nav_category_press, resources.getString(R.string.nav_bar_category))
            .setInactiveIconResource(R.drawable.btn_nav_category_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)

        // 购物车
        val cartItem = BottomNavigationItem(R.drawable.btn_nav_cart_press, resources.getString(R.string.nav_bar_cart))
            .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)

        mCartBadgeItem = TextBadgeItem()
        cartItem.setBadgeItem(mCartBadgeItem)

        // 消息
        val msgItem = BottomNavigationItem(R.drawable.btn_nav_msg_press, resources.getString(R.string.nav_bar_msg))
            .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)

        mMsgBadgeItem = ShapeBadgeItem()
        mMsgBadgeItem.setShape(ShapeBadgeItem.SHAPE_OVAL)
        msgItem.setBadgeItem(mMsgBadgeItem)

        // 我的
        val mineItem = BottomNavigationItem(R.drawable.btn_nav_user_press, resources.getString(R.string.nav_bar_user))
            .setInactiveIconResource(R.drawable.btn_nav_user_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)

        setMode(MODE_FIXED)
        setBackgroundStyle(BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)

        addItem(homeItem)
            .addItem(categoryItem)
            .addItem(cartItem)
            .addItem(msgItem)
            .addItem(mineItem)
            .setFirstSelectedPosition(0)
            .initialise()
    }

    fun checkCartBadge(count: Int) {
        if (count == 0) {
            mCartBadgeItem.hide()
        } else {
            mCartBadgeItem.show()
            mCartBadgeItem.setText("$count")
        }
    }

    fun checkMsgBadge(isVisible: Boolean) {
        if (isVisible) {
            mMsgBadgeItem.show()
        } else {
            mMsgBadgeItem.hide()
        }
    }
}
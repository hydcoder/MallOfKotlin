package com.hyd.goodscenter.ui.activity

import android.os.Bundle
import com.hyd.base.ui.activity.BaseActivity
import com.hyd.goodscenter.R
import com.hyd.goodscenter.ui.fragment.CartFragment

/**
 * Created by hydCoder on 2019/8/6.
 * 以梦为马，明日天涯。
 */
class CartActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment: CartFragment = supportFragmentManager.findFragmentById(R.id.fragment_cart) as CartFragment
        fragment.setLeftIvVisible(true)
    }

}
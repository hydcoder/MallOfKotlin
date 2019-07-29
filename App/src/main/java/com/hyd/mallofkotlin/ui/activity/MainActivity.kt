package com.hyd.mallofkotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hyd.mallofkotlin.R
import com.hyd.mallofkotlin.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkCartBadge(10)
        mBottomNavBar.checkMsgBadge(false)

        initView()
    }

    private fun initView() {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer, HomeFragment())
        manager.commit()
    }
}



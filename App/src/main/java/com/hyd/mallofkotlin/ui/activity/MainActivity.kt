package com.hyd.mallofkotlin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.hyd.base.common.AppManager
import com.hyd.base.ui.activity.BaseActivity
import com.hyd.goodscenter.ui.fragment.CategoryFragment
import com.hyd.mallofkotlin.R
import com.hyd.mallofkotlin.ui.fragment.HomeFragment
import com.hyd.mallofkotlin.ui.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : BaseActivity() {

    private val mFragments: Stack<Fragment> = Stack()

    private val mHomeFragment: HomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment: CategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment: HomeFragment by lazy { HomeFragment() }
    private val mMsgFragment: HomeFragment by lazy { HomeFragment() }
    private val mMineFragment: MineFragment by lazy { MineFragment() }

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        initView()
        initFragment()
        initBottomNav()
        changeFragment(0)
    }


    private fun initView() {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer, HomeFragment())
        manager.commit()
    }

    /**
     * 初始化fragment栈管理
     */
    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContainer, mHomeFragment)
        manager.add(R.id.mContainer, mCategoryFragment)
        manager.add(R.id.mContainer, mCartFragment)
        manager.add(R.id.mContainer, mMsgFragment)
        manager.add(R.id.mContainer, mMineFragment)
        manager.commit()

        mFragments.add(mHomeFragment)
        mFragments.add(mCategoryFragment)
        mFragments.add(mCartFragment)
        mFragments.add(mMsgFragment)
        mFragments.add(mMineFragment)
    }

    private fun initBottomNav() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(0)
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mFragments) {
            manager.hide(fragment)
        }
        manager.show(mFragments[position])
        manager.commit()
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出应用程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }
}



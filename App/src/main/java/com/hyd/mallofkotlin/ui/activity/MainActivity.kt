package com.hyd.mallofkotlin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.hyd.base.common.AppManager
import com.hyd.base.ui.activity.BaseActivity
import com.hyd.base.utils.AppPrefsUtils
import com.hyd.goodscenter.common.GoodsConstant
import com.hyd.goodscenter.event.UpdateCartSizeEvent
import com.hyd.goodscenter.ui.fragment.CartFragment
import com.hyd.goodscenter.ui.fragment.CategoryFragment
import com.hyd.mallofkotlin.R
import com.hyd.mallofkotlin.ui.fragment.HomeFragment
import com.hyd.mallofkotlin.ui.fragment.MineFragment
import com.hyd.messagecenter.ui.fragment.MessageFragment
import com.hyd.provider.event.MessageBadgeEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : BaseActivity() {

    private val mFragments: Stack<Fragment> = Stack()

    private val mHomeFragment: HomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment: CategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment: CartFragment by lazy { CartFragment() }
    private val mMsgFragment: MessageFragment by lazy { MessageFragment() }
    private val mMineFragment: MineFragment by lazy { MineFragment() }

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initBottomNav()
        changeFragment(0)
        initObserve()
        loadCartSize()
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

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
            .subscribe {
                loadCartSize()
            }.registerInBus(this)
        Bus.observe<MessageBadgeEvent>()
            .subscribe {
                mBottomNavBar.checkMsgBadge(it.isVisible)
            }.registerInBus(this)
    }

    private fun loadCartSize() {
        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}



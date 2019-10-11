package com.hyd.mallofkotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyd.base.ext.loadImage
import com.hyd.base.ui.fragment.BaseFragment
import com.hyd.base.utils.AppPrefsUtils
import com.hyd.mallofkotlin.R
import com.hyd.mallofkotlin.ui.activity.SettingActivity
import com.hyd.order.common.OrderConstant
import com.hyd.order.common.OrderStatus
import com.hyd.order.ui.activity.OrderActivity
import com.hyd.order.ui.activity.ShipAddressActivity
import com.hyd.provider.common.ProviderConstant
import com.hyd.provider.common.afterLogin
import com.hyd.provider.common.isLogin
import com.hyd.user.ui.activity.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Created by hydCoder on 2019/7/26.
 * 以梦为马，明日天涯。
 */
class MineFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initView() {
        mUserIconIv.setOnClickListener(this)
        mUserNameTv.setOnClickListener(this)

        mWaitPayOrderTv.setOnClickListener(this)
        mWaitConfirmOrderTv.setOnClickListener(this)
        mCompleteOrderTv.setOnClickListener(this)
        mAllOrderTv.setOnClickListener(this)
        mShareTv.setOnClickListener(this)
        mSettingTv.setOnClickListener(this)
        mAddressTv.setOnClickListener(this)
    }

    private fun loadData() {
        if (isLogin()) {
            mUserIconIv.loadImage(AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON))
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                afterLogin {
                    activity!!.startActivity<UserInfoActivity>()
                }
            }

            R.id.mWaitPayOrderTv -> {
                afterLogin {
                    activity!!.startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
                }
            }

            R.id.mWaitConfirmOrderTv -> {
                afterLogin {
                    activity!!.startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
                }
            }
            R.id.mCompleteOrderTv -> {
                afterLogin {
                    activity!!.startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
                }
            }
            R.id.mAllOrderTv -> {
                afterLogin {
                    activity!!.startActivity<OrderActivity>()
                }
            }

            R.id.mShareTv -> {
                toast(R.string.coming_soon_tip)
            }
            R.id.mAddressTv -> {
                activity!!.startActivity<ShipAddressActivity>()
            }
            R.id.mSettingTv -> {
                activity!!.startActivity<SettingActivity>()
            }
        }
    }
}
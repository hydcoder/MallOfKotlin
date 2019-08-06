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
import com.hyd.provider.common.ProviderConstant
import com.hyd.provider.common.isLogin
import com.hyd.user.ui.activity.LoginActivity
import com.hyd.user.ui.activity.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.startActivity

/**
 * Created by hydCoder on 2019/7/26.
 * 以梦为马，明日天涯。
 */
class MineFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        mSettingTv.setOnClickListener(this)
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
                if (isLogin()) {
                    activity!!.startActivity<UserInfoActivity>()
                } else {
                    activity!!.startActivity<LoginActivity>()
                }
            }
            R.id.mSettingTv -> {
                activity!!.startActivity<SettingActivity>()
            }
        }
    }
}
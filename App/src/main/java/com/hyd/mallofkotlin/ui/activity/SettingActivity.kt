package com.hyd.mallofkotlin.ui.activity

import android.os.Bundle
import android.view.View
import com.hyd.base.ui.activity.BaseActivity
import com.hyd.mallofkotlin.R
import com.hyd.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * Created by hydCoder on 2019/7/30.
 * 以梦为马，明日天涯。
 */
class SettingActivity: BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        initView()
    }

    private fun initView() {
        mLogoutBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mLogoutBtn -> {
                UserPrefsUtils.putUserInfo(null)
                finish()
            }
        }
    }
}
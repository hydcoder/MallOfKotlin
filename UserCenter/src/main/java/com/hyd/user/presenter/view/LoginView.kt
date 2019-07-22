package com.hyd.user.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.user.data.protocal.UserInfo

interface LoginView: BaseView {
    fun onLoginResult(result: UserInfo)
}
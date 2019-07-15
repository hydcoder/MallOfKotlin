package com.hyd.user.presenter.view

import com.hyd.base.presenter.view.BaseView

interface RegisterView: BaseView {
    fun onRegisterResult(result:String)
}
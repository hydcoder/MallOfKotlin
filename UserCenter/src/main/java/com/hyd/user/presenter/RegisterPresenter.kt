package com.hyd.user.presenter

import com.hyd.base.presenter.BasePresenter
import com.hyd.user.presenter.view.RegisterView

class RegisterPresenter: BasePresenter<RegisterView>() {
    fun register(mobile:String, pwd:String){
        /*
        业务逻辑
         */
        mView.onRegisterResult("注册成功")
    }
}
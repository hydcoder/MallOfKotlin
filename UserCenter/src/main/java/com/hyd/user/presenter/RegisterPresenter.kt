package com.hyd.user.presenter

import com.hyd.base.ext.execute
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.rx.BaseSubscribe
import com.hyd.user.presenter.view.RegisterView
import com.hyd.user.service.impl.UserServiceImpl

class RegisterPresenter: BasePresenter<RegisterView>() {
    fun register(mobile:String, verifyCode:String, pwd:String){

        val userService = UserServiceImpl()

        userService.register(mobile, verifyCode, pwd)
            .execute(object : BaseSubscribe<Boolean>(){
                override fun onNext(t: Boolean) {
                    mView.onRegisterResult(t)
                }
            })
    }
}
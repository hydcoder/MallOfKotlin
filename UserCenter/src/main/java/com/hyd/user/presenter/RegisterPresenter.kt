package com.hyd.user.presenter

import com.hyd.base.ext.execute
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.rx.BaseSubscribe
import com.hyd.user.presenter.view.RegisterView
import com.hyd.user.service.UserService
import javax.inject.Inject

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile:String, verifyCode:String, pwd:String){

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()

        userService.register(mobile, verifyCode, pwd)
            .execute(object : BaseSubscribe<Boolean>(mView){
                override fun onNext(t: Boolean) {
                    if (t)
                        mView.onRegisterResult("注册成功")
                }
            }, lifecycleProvider)
    }
}
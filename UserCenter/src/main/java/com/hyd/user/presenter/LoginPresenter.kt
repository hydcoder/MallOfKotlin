package com.hyd.user.presenter

import com.hyd.base.ext.execute
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.rx.BaseSubscriber
import com.hyd.user.data.protocal.UserInfo
import com.hyd.user.presenter.view.LoginView
import com.hyd.user.service.UserService
import javax.inject.Inject

class LoginPresenter @Inject constructor(): BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, pwd: String, pushId: String){

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()

        userService.login(mobile, pwd, pushId)
            .execute(object : BaseSubscriber<UserInfo>(mView){
                override fun onNext(t: UserInfo) {
                    mView.onLoginResult(t)
                }
            }, lifecycleProvider)
    }
}
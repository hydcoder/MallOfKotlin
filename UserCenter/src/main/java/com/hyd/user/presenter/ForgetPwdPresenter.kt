package com.hyd.user.presenter

import com.hyd.base.ext.execute
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.rx.BaseSubscriber
import com.hyd.user.presenter.view.ForgetPwdView
import com.hyd.user.service.UserService
import javax.inject.Inject

class ForgetPwdPresenter @Inject constructor(): BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun forgetPwd(mobile: String, verifyCode: String){

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()

        userService.forgetPwd(mobile, verifyCode)
            .execute(object : BaseSubscriber<Boolean>(mView){
                override fun onNext(t: Boolean) {
                    if (t) {
                        mView.onForgetResult("验证成功")
                    }
                }
            }, lifecycleProvider)
    }
}
package com.hyd.user.presenter

import com.hyd.base.ext.execute
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.rx.BaseSubscriber
import com.hyd.user.presenter.view.ResetPwdView
import com.hyd.user.service.UserService
import javax.inject.Inject

class ResetPwdPresenter @Inject constructor(): BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile: String, pwd: String){

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()

        userService.resetPwd(mobile, pwd)
            .execute(object : BaseSubscriber<Boolean>(mView){
                override fun onNext(t: Boolean) {
                    if (t) {
                        mView.onResetResult("密码重置成功")
                    }
                }
            }, lifecycleProvider)
    }
}
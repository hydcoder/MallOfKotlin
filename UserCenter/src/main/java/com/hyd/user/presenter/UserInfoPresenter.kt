package com.hyd.user.presenter

import com.hyd.base.ext.execute
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.rx.BaseSubscriber
import com.hyd.user.presenter.view.UserInfoView
import com.hyd.user.service.UploadService
import com.hyd.user.service.UserService
import com.kotlin.base.utils.NetWorkUtils
import javax.inject.Inject

class UserInfoPresenter @Inject constructor(): BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService

    fun getUploadToken() {
        if (!NetWorkUtils.isNetWorkAvailable(context)) {
            return
        }
        mView.showLoading()
        uploadService.getUploadToken().execute(object :BaseSubscriber<String>(mView){
            override fun onNext(t: String) {
                mView.getUploadTokenResult(t)
            }
        }, lifecycleProvider)
    }
}
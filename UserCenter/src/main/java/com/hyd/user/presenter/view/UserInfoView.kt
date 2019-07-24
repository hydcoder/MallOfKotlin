package com.hyd.user.presenter.view

import com.hyd.base.presenter.view.BaseView

interface UserInfoView: BaseView {
    fun getUploadTokenResult(result: String)
}
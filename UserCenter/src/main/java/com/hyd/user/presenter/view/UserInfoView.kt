package com.hyd.user.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.user.data.protocal.UserInfo

interface UserInfoView: BaseView {
    fun getUploadTokenResult(result: String)
    fun editUserResult(result: UserInfo)
}
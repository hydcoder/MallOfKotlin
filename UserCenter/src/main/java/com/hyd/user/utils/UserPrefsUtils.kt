package com.hyd.user.utils

import com.hyd.base.common.BaseConstant
import com.hyd.base.utils.AppPrefsUtils
import com.hyd.provider.common.ProviderConstant
import com.hyd.user.data.protocal.UserInfo

/*
    本地存储用户相关信息
 */
object UserPrefsUtils {

    /*
        退出登录时，传入null,清空存储
     */
    fun putUserInfo(userInfo: UserInfo?) {
        AppPrefsUtils.putString(BaseConstant.KEY_SP_TOKEN, userInfo?.id ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_ICON, userInfo?.userIcon ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_NAME, userInfo?.userName ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_MOBILE, userInfo?.userMobile ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_GENDER, userInfo?.userGender ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_SIGN, userInfo?.userSign ?: "")
    }
}

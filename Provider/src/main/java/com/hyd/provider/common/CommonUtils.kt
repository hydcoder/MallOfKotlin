package com.hyd.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.hyd.base.common.BaseConstant
import com.hyd.base.utils.AppPrefsUtils
import com.hyd.provider.router.RouterPath

/**
 * Created by hydCoder on 2019/7/30.
 * 以梦为马，明日天涯。
 */
fun isLogin(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}

fun afterLogin(method: () -> Unit) {
    if (isLogin()) {
        method()
    } else {
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}
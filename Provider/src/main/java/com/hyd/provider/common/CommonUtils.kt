package com.hyd.provider.common

import com.hyd.base.common.BaseConstant
import com.hyd.base.utils.AppPrefsUtils

/**
 * Created by hydCoder on 2019/7/30.
 * 以梦为马，明日天涯。
 */
fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}
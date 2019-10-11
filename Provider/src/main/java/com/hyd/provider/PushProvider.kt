package com.hyd.provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by hydCoder on 2019/8/19.
 * 以梦为马，明日天涯。
 */
interface PushProvider: IProvider {

    fun getPushId(): String
}
package com.hyd.messagecenter.provider

import android.content.Context
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.facade.annotation.Route
import com.hyd.provider.PushProvider
import com.hyd.provider.router.RouterPath

/**
 * Created by hydCoder on 2019/8/19.
 * 以梦为马，明日天涯。
 */
/*
    模块间接口调用
    提供PushId的实现
 */
@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
class PushProviderImpl: PushProvider {

    var mContext: Context? = null

    override fun getPushId(): String {
        return JPushInterface.getRegistrationID(mContext)
    }

    override fun init(context: Context?) {
        mContext = context
    }
}
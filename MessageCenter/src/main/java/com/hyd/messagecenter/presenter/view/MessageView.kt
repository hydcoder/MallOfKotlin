package com.hyd.messagecenter.presenter.view

import com.hyd.base.presenter.view.BaseView
import com.hyd.messagecenter.data.protocal.Message

/**
 * Created by hydCoder on 2019/10/11.
 * 以梦为马，明日天涯。
 */
/*
    消息列表 视图回调
 */
interface MessageView : BaseView {

    //获取消息列表回调
    fun onGetMessageResult(result:MutableList<Message>?)
}
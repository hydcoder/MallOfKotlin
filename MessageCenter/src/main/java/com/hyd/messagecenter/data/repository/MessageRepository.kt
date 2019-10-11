package com.hyd.messagecenter.data.repository

import com.hyd.base.data.net.RetrofitFactory
import com.hyd.base.data.protocal.BaseResp
import com.hyd.messagecenter.data.api.MessageApi
import com.hyd.messagecenter.data.protocal.Message
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/10/11.
 * 以梦为马，明日天涯。
 */
/*
   消息数据层
 */
class MessageRepository @Inject constructor() {

    /*
        获取消息列表
     */
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>> {
        return RetrofitFactory.instance.create(MessageApi::class.java).getMessageList()
    }
}
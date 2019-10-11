package com.hyd.messagecenter.service

import com.hyd.messagecenter.data.protocal.Message
import rx.Observable

/**
 * Created by hydCoder on 2019/10/11.
 * 以梦为马，明日天涯。
 */
/*
   消息业务接口
 */
interface MessageService {
    //获取消息列表
    fun getMessageList(): Observable<MutableList<Message>?>

}
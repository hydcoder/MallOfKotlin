package com.hyd.messagecenter.service.impl

import com.hyd.base.ext.convert
import com.hyd.messagecenter.data.protocal.Message
import com.hyd.messagecenter.data.repository.MessageRepository
import com.hyd.messagecenter.service.MessageService
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/10/11.
 * 以梦为马，明日天涯。
 */
/*
   消息业务层
 */
class MessageServiceImpl @Inject constructor(): MessageService {

    @Inject
    lateinit var repository: MessageRepository

    /*
        获取消息列表
     */
    override fun getMessageList(): Observable<MutableList<Message>?> {
        return repository.getMessageList().convert()
    }

}
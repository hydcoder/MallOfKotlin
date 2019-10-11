package com.hyd.messagecenter.injection.module

import com.hyd.messagecenter.service.MessageService
import com.hyd.messagecenter.service.impl.MessageServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/10/11.
 * 以梦为马，明日天涯。
 */
/*
    消息模块业务注入
 */
@Module
class MessageModule {

    @Provides
    fun provideMessageService(messageService: MessageServiceImpl): MessageService {
        return  messageService
    }

}
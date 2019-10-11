package com.hyd.messagecenter.presenter

import com.hyd.base.ext.execute
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.rx.BaseSubscriber
import com.hyd.messagecenter.data.protocal.Message
import com.hyd.messagecenter.presenter.view.MessageView
import com.hyd.messagecenter.service.MessageService
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/10/11.
 * 以梦为马，明日天涯。
 */
/*
    消息列表 Presenter
 */
class MessagePresenter @Inject constructor() : BasePresenter<MessageView>() {

    @Inject
    lateinit var messageService: MessageService

    /*
        获取消息列表
     */
    fun getMessageList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        messageService.getMessageList().execute(object : BaseSubscriber<MutableList<Message>?>(mView) {
            override fun onNext(t: MutableList<Message>?) {
                mView.onGetMessageResult(t)
            }
        }, lifecycleProvider)

    }
}
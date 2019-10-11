package com.hyd.messagecenter.data.api

import com.hyd.base.data.protocal.BaseResp
import com.hyd.messagecenter.data.protocal.Message
import retrofit2.http.POST
import rx.Observable

/**
 * Created by hydCoder on 2019/10/11.
 * 以梦为马，明日天涯。
 */
/*
    消息 接口
 */
interface MessageApi {

    /*
        获取消息列表
     */
    @POST("msg/getList")
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>>

}
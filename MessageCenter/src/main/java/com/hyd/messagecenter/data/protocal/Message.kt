package com.hyd.messagecenter.data.protocal

/**
 * Created by hydCoder on 2019/10/11.
 * 以梦为马，明日天涯。
 */
/*
    消息实体
 */
data class Message(
    val msgIcon: String,
    val msgTitle: String,
    val msgContent: String,
    val msgTime: String
)
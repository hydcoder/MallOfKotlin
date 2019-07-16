package com.hyd.base.data.protocal

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
class BaseResp<T>(val status: Int, val message: String, val data:T)
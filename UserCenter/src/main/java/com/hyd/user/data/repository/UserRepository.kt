package com.hyd.user.data.repository

import com.hyd.base.data.net.RetrofitFactory
import com.hyd.base.data.protocal.BaseResp
import com.hyd.user.data.api.UserApi
import com.hyd.user.data.protocal.RegisterReq
import rx.Observable

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
class UserRepository {

    fun register(mobile: String, verifyCode: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).register(
            RegisterReq(mobile, verifyCode, pwd))
    }
}
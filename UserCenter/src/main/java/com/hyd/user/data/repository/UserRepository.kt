package com.hyd.user.data.repository

import com.hyd.base.data.net.RetrofitFactory
import com.hyd.base.data.protocal.BaseResp
import com.hyd.user.data.api.UserApi
import com.hyd.user.data.protocal.*
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
class UserRepository @Inject constructor(){

    fun register(mobile: String, verifyCode: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).register(
            RegisterReq(mobile, verifyCode, pwd))
    }

    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java).login(
            LoginReq(mobile, pwd, pushId)
        )
    }

    fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).forgetPwd(
            ForgetPwdReq(mobile, verifyCode)
        )
    }

    fun resetPwd(mobile: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).resetPwd(
            ResetPwdReq(mobile, pwd)
        )
    }
}
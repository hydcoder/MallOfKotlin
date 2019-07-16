package com.hyd.user.service.impl

import com.hyd.base.data.protocal.BaseResp
import com.hyd.base.rx.BaseException
import com.hyd.user.data.repository.UserRepository
import com.hyd.user.service.UserService
import rx.Observable
import rx.functions.Func1

/**
 * Author: Created by HYD on 2019/7/16.
 * 以梦为马，明日天涯。
 */
class UserServiceImpl : UserService {
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        val repository = UserRepository()

        return repository.register(mobile, verifyCode, pwd).flatMap(object : Func1<BaseResp<String>, Observable<Boolean>>{
            override fun call(t: BaseResp<String>): Observable<Boolean> {
                if (t.status != 0) {
                    return Observable.error(BaseException(t.status, t.message))
                }
                return Observable.just(true)
            }
        })
    }
}
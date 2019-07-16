package com.hyd.user.service.impl

import com.hyd.user.service.UserService
import rx.Observable

/**
 * Author: Created by HYD on 2019/7/16.
 * 以梦为马，明日天涯。
 */
class UserServiceImpl : UserService {
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }
}
package com.hyd.user.service

import rx.Observable

/**
 * Author: Created by HYD on 2019/7/16.
 * 以梦为马，明日天涯。
 */
open interface UserService {
    fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean>
}
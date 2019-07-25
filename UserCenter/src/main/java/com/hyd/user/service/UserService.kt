package com.hyd.user.service

import com.hyd.user.data.protocal.UserInfo
import rx.Observable

/**
 * Author: Created by HYD on 2019/7/16.
 * 以梦为马，明日天涯。
 */
open interface UserService {
    fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean>
    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>
    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>
    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo>
}
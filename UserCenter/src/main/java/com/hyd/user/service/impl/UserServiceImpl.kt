package com.hyd.user.service.impl

import com.hyd.base.ext.convertBoolean
import com.hyd.user.data.repository.UserRepository
import com.hyd.user.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * Author: Created by HYD on 2019/7/16.
 * 以梦为马，明日天涯。
 */
class UserServiceImpl @Inject constructor(): UserService {

    @Inject
    lateinit var repository:UserRepository

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        return repository.register(mobile, verifyCode, pwd).convertBoolean()
    }
}
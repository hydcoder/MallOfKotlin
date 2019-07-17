package com.hyd.user.injection.module

import com.hyd.user.service.UserService
import com.hyd.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@Module
class UserModule {

    @Provides
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }
}
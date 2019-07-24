package com.hyd.user.injection.module

import com.hyd.user.service.UploadService
import com.hyd.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@Module
class UploadModule {

    @Provides
    fun providesUploadService(service: UploadServiceImpl): UploadService {
        return service
    }
}
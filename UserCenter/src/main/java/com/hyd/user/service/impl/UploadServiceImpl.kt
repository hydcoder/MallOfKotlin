package com.hyd.user.service.impl

import com.hyd.base.ext.convert
import com.hyd.user.data.repository.UploadRepository
import com.hyd.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * Author: Created by HYD on 2019/7/16.
 * 以梦为马，明日天涯。
 */
class UploadServiceImpl @Inject constructor(): UploadService {

    @Inject
    lateinit var repository: UploadRepository

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }
}
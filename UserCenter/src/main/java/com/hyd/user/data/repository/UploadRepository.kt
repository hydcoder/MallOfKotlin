package com.hyd.user.data.repository

import com.hyd.base.data.net.RetrofitFactory
import com.hyd.base.data.protocal.BaseResp
import com.hyd.user.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
class UploadRepository @Inject constructor(){

    fun getUploadToken(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }
}
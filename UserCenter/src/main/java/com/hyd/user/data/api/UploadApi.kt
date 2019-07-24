package com.hyd.user.data.api

import com.hyd.base.data.protocal.BaseResp
import retrofit2.http.POST
import rx.Observable

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
interface UploadApi {
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>
}
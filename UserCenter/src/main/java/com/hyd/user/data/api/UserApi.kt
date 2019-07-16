package com.hyd.user.data.api

import com.hyd.base.data.protocal.BaseResp
import com.hyd.user.data.protocal.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>
}
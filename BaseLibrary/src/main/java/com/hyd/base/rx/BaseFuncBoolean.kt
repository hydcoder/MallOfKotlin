package com.hyd.base.rx

import com.hyd.base.common.ResultCode
import com.hyd.base.data.protocal.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by hydCoder on 2019/7/18.
 * 以梦为马，明日天涯。
 */
class BaseFuncBoolean<T>: Func1<BaseResp<T>, Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }
}
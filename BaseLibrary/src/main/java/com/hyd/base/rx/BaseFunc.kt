package com.hyd.base.rx

import com.hyd.base.data.protocal.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by hydCoder on 2019/7/18.
 * 以梦为马，明日天涯。
 */
class BaseFunc<T>: Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }
}
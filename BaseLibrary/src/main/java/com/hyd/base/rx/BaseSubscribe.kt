package com.hyd.base.rx

import rx.Subscriber

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
open class BaseSubscribe<T>: Subscriber<T>() {

    override fun onNext(t: T) {
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }
}
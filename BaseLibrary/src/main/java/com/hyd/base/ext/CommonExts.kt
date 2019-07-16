package com.hyd.base.ext

import com.hyd.base.rx.BaseSubscribe
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscribe<T>) {
    this.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(subscriber)
}
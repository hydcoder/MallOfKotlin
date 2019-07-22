package com.hyd.base.rx

import com.hyd.base.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
open class BaseSubscriber<T>(private val baseView: BaseView): Subscriber<T>() {

    override fun onNext(t: T) {
    }

    override fun onCompleted() {
        baseView.hideLoading()
    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()
        e?.message?.let { baseView.onError(it) }
    }
}
package com.hyd.base.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.hyd.base.data.protocal.BaseResp
import com.hyd.base.rx.BaseFunc
import com.hyd.base.rx.BaseFuncBoolean
import com.hyd.base.rx.BaseSubscribe
import com.kotlin.base.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by hydCoder on 2019/7/16.
 * 以梦为马，明日天涯。
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscribe<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
        .compose(lifecycleProvider.bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .subscribe(subscriber)
}

fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}

fun Button.enable(editText: EditText, method: () -> Boolean) {
    val btn = this
    editText.addTextChangedListener(object: DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}
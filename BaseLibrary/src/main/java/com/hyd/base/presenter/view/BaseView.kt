package com.hyd.base.presenter.view

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text: String)
}
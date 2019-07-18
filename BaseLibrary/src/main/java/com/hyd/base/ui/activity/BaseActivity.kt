package com.hyd.base.ui.activity

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.hyd.base.R
import com.hyd.base.common.AppManager
import com.hyd.base.common.StatusBarCompat
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

open class BaseActivity : RxAppCompatActivity(){

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 沉浸式状态栏
        StatusBarCompat.compat(this, getColor(R.color.common_blue))
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
}
package com.hyd.user.injection.component

import com.hyd.base.injection.PerCompernentScope
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.user.injection.module.UserModule
import com.hyd.user.ui.activity.ForgetPwdActivity
import com.hyd.user.ui.activity.LoginActivity
import com.hyd.user.ui.activity.RegisterActivity
import com.hyd.user.ui.activity.ResetPwdActivity
import dagger.Component

/**
 * Created by hydCoder on 2019/7/17.
 * 以梦为马，明日天涯。
 */
@PerCompernentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
}
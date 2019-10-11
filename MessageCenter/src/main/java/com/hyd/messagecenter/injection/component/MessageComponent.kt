package com.hyd.messagecenter.injection.component

import com.hyd.base.injection.PerComponentScope
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.messagecenter.injection.module.MessageModule
import com.hyd.messagecenter.ui.fragment.MessageFragment
import dagger.Component

/**
 * Created by hydCoder on 2019/10/11.
 * 以梦为马，明日天涯。
 */
/*
    消息模块注入组件
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(MessageModule::class))
interface MessageComponent{
    fun inject(fragment: MessageFragment)
}
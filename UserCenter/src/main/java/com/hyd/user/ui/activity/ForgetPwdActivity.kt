package com.hyd.user.ui.activity

import android.os.Bundle
import com.hyd.base.ext.enable
import com.hyd.base.ext.onClick
import com.hyd.base.ui.activity.BaseMvpActivity
import com.hyd.user.R
import com.hyd.user.injection.component.DaggerUserComponent
import com.hyd.user.injection.module.UserModule
import com.hyd.user.presenter.ForgetPwdPresenter
import com.hyd.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ForgetPwdActivity: BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView {

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    private fun initView() {
        mNextBtn.enable(mMobileEt) { setButtonEnable() }
        mNextBtn.enable(mVerifyCodeEt) { setButtonEnable() }

        mNextBtn.onClick {
            mPresenter.forgetPwd(mMobileEt.text.toString(), mVerifyCodeEt.text.toString())
        }
        mVerifyCodeBtn.onClick {
            toast("验证码发送成功")
            mVerifyCodeBtn.requestSendVerifyNumber()
        }
    }

    private fun setButtonEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }

    override fun onForgetResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
    }
}

package com.hyd.user.ui.activity

import android.os.Bundle
import android.view.View
import com.hyd.base.common.AppManager
import com.hyd.base.ext.enable
import com.hyd.base.ui.activity.BaseMvpActivity
import com.hyd.user.R
import com.hyd.user.injection.component.DaggerUserComponent
import com.hyd.user.injection.module.UserModule
import com.hyd.user.presenter.RegisterPresenter
import com.hyd.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity: BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    private var pressTime: Long = 0

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    private fun initView() {
        mRegisterBtn.enable(mMobileEt) { setButtonEnable() }
        mRegisterBtn.enable(mVerifyCodeEt) { setButtonEnable() }
        mRegisterBtn.enable(mPwdEt) { setButtonEnable() }
        mRegisterBtn.enable(mPwdConfirmEt) { setButtonEnable() }

        mRegisterBtn.setOnClickListener(this)
        mVerifyCodeBtn.setOnClickListener(this)
    }

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出应用程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.mRegisterBtn -> {
                mPresenter.register(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
            }
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
            }
        }
    }

    private fun setButtonEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mVerifyCodeEt.text.isNullOrEmpty().not() and
                mPwdEt.text.isNullOrEmpty().not() and
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}

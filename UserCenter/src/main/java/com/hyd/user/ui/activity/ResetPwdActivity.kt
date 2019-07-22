package com.hyd.user.ui.activity

import android.os.Bundle
import com.hyd.base.ext.enable
import com.hyd.base.ext.onClick
import com.hyd.base.ui.activity.BaseMvpActivity
import com.hyd.user.R
import com.hyd.user.injection.component.DaggerUserComponent
import com.hyd.user.injection.module.UserModule
import com.hyd.user.presenter.ResetPwdPresenter
import com.hyd.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

class ResetPwdActivity: BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()
    }

    private fun initView() {
        mConfirmBtn.enable(mPwdEt) { setButtonEnable() }
        mConfirmBtn.enable(mPwdConfirmEt) { setButtonEnable() }

        mConfirmBtn.onClick {
            if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()) {
                toast("密码不一致")
                return@onClick
            }
            mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.text.toString())
        }
    }

    private fun setButtonEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() and
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    override fun onResetResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }
}

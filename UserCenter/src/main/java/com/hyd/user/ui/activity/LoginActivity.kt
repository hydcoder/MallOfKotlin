package com.hyd.user.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hyd.base.ext.enable
import com.hyd.base.ui.activity.BaseMvpActivity
import com.hyd.provider.PushProvider
import com.hyd.provider.router.RouterPath
import com.hyd.user.R
import com.hyd.user.data.protocal.UserInfo
import com.hyd.user.injection.component.DaggerUserComponent
import com.hyd.user.injection.module.UserModule
import com.hyd.user.presenter.LoginPresenter
import com.hyd.user.presenter.view.LoginView
import com.hyd.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    @Autowired(name = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
    @JvmField
    var mPushProvider:PushProvider? = null

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.activity_login)


        initView()
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt) { setButtonEnable() }
        mLoginBtn.enable(mPwdEt) { setButtonEnable() }

        mLoginBtn.setOnClickListener(this)
        mForgetPwdTv.setOnClickListener(this)
        mHeaderBar.getRightView().setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), mPushProvider?.getPushId() ?: "")
            }
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }
            R.id.mForgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
        }
    }

    private fun setButtonEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mPwdEt.text.isNullOrEmpty().not()
    }

    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        UserPrefsUtils.putUserInfo(result)
        finish()
    }

}

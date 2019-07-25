package com.hyd.user.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.hyd.base.common.BaseConstant
import com.hyd.base.ext.onClick
import com.hyd.base.ui.activity.BaseMvpActivity
import com.hyd.base.utils.AppPrefsUtils
import com.hyd.provider.common.ProviderConstant
import com.hyd.user.R
import com.hyd.user.data.protocal.UserInfo
import com.hyd.user.injection.component.DaggerUserComponent
import com.hyd.user.injection.module.UserModule
import com.hyd.user.presenter.UserInfoPresenter
import com.hyd.user.presenter.view.UserInfoView
import com.hyd.user.utils.UserPrefsUtils
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener {

    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    private lateinit var mLocalFile: File

    private val mUploadManager: UploadManager by lazy { UploadManager() }

    private var mRemoteFileUrl: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserGender: String? = null
    private var mUserMobile: String? = null
    private var mUserSign: String? = null

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        mUserIconView.onClick {
            AlertView("图片选择", null, "取消", null, arrayOf("拍照", "相册选择"), this,
                AlertView.Style.ActionSheet, OnItemClickListener { o, position ->
                    mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                    when (position) {
                        0 -> {
                            createTempFile()
                            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                        }
                        1 -> {
                            mTakePhoto.onPickFromGallery()
                        }
                    }
                }).show()
        }

        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(
                mRemoteFileUrl!!, mUserNameEt.text?.toString() ?: "",
                if (mGenderMaleRb.isChecked) "0" else "1",
                mUserSignEt.text?.toString() ?: "")
        }
    }

    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mRemoteFileUrl = mUserIcon
        if (mUserIcon != "") {
            GlideUtils.loadUrlImage(this, mRemoteFileUrl!!, mUserIconIv)
        }

        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserNameEt.setText(mUserName)

        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }

        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserMobileTv.text = mUserMobile

        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)
        mUserSignEt.setText(mUserSign)
    }

    override fun takeSuccess(result: TResult?) {
        Log.i("TakePhoto", "原始的图片路径：${result?.image?.originalPath}")
        Log.i("TakePhoto", "压缩的图片路径：${result?.image?.compressPath}")

        mLocalFile = File("${result?.image?.compressPath}")
        mPresenter.getUploadToken()
    }

    override fun takeCancel() {
        Log.i("TakePhoto", "取消选择")
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto", msg)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    private fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        mTempFile = File(filesDir, tempFileName)
    }

    override fun getUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFile, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")

                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)
            }

        }, null)
    }

    override fun editUserResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }
}

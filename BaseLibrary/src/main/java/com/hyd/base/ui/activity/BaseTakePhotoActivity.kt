package com.hyd.base.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.hyd.base.common.BaseApplication
import com.hyd.base.injection.component.ActivityComponent
import com.hyd.base.injection.component.DaggerActivityComponent
import com.hyd.base.injection.module.ActivityModule
import com.hyd.base.injection.module.LifecycleProviderModule
import com.hyd.base.presenter.BasePresenter
import com.hyd.base.presenter.view.BaseView
import com.hyd.base.widgets.ProgressLoading
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.DateUtils
import org.jetbrains.anko.toast
import java.io.File
import javax.inject.Inject

open abstract class BaseTakePhotoActivity<T : BasePresenter<*>> : BaseActivity(), BaseView, TakePhoto.TakeResultListener {

    lateinit var activityComponent: ActivityComponent
    private lateinit var loadingDialog: ProgressLoading

    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    //Presenter泛型，Dagger注入
    @Inject
    lateinit var mPresenter: T

    override fun showLoading() {
        loadingDialog.showLoading()
    }

    override fun hideLoading() {
        loadingDialog.hideLoading()
    }

    override fun onError(text: String) {
        toast(text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()

        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)

        loadingDialog = ProgressLoading.create(this)
    }

    private fun initActivityInjection() {
        activityComponent =
            DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this)).lifecycleProviderModule(LifecycleProviderModule(this)).build()
    }

    abstract fun injectComponent()

    override fun takeSuccess(result: TResult?) {
        Log.i("TakePhoto", "原始的图片路径：${result?.image?.originalPath}")
        Log.i("TakePhoto", "压缩的图片路径：${result?.image?.compressPath}")
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

    protected fun showAlertView() {
        AlertView("图片选择", null, "取消", null, arrayOf("拍照", "相册选择"), this,
            AlertView.Style.ActionSheet, OnItemClickListener { _, position ->
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
}
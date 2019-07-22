package com.hyd.base.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.hyd.base.R
import org.jetbrains.anko.find

/**
 * Created by hydCoder on 2019/7/19.
 * 以梦为马，明日天涯。
 */
class ProgressLoading private constructor(context: Context, theme: Int) : Dialog(context, theme) {

    companion object {
        private lateinit var mDialog: ProgressLoading
        private var animDrawable: AnimationDrawable? = null

        fun create(context: Context): ProgressLoading {
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER

            val lp = mDialog.window.attributes
            lp.dimAmount = 0.2f
            mDialog.window.attributes = lp

            val loading = mDialog.find<ImageView>(R.id.iv_loading)
            animDrawable = loading.background as AnimationDrawable?

            return mDialog
        }
    }

    fun showLoading() {
        mDialog.show()
        animDrawable!!.start()
    }

    fun hideLoading() {
        mDialog.dismiss()
        animDrawable!!.stop()
    }
}
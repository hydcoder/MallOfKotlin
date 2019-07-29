package com.hyd.base.widgets

import android.content.Context
import android.widget.ImageView
import com.kotlin.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 * Created by hydCoder on 2019/7/26.
 * 以梦为马，明日天涯。
 */
class BannerImageLoader: ImageLoader() {

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        GlideUtils.loadUrlImage(context!!, path.toString(), imageView!!)
    }
}
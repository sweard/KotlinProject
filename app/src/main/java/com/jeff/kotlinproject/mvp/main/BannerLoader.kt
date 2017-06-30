package com.jeff.kotlinproject.mvp.main

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 * Created by .F on 2017/6/30.
 */
class BannerLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        Glide.with(context).load(path).into(imageView)
    }
}
package com.jeff.kotlinproject.mvp.hybrid

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface
import android.widget.Toast


/**
 * Created by jeff on 17-7-31.
 */
class JavaBridge(val context: Context) {

    @JavascriptInterface
    fun showToast() {
        Toast.makeText(context, "我是Android原生方法", Toast.LENGTH_LONG).show()
    }

    @JavascriptInterface
    fun playVideo(id: Int, title: String, videoUrl: String) {
        val intent = Intent()
        intent.setDataAndType(Uri.parse(videoUrl), "video/*")
        context.startActivity(intent)
    }

}
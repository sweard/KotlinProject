package com.jeff.kotlinproject.mvp.hybrid

import android.text.TextUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_mywebview.*

/**
 * Created by jeff on 17-7-31.
 */
class MyWebView : BaseActivity() {
    lateinit var webView: WebView


    override val layoutId: Int
        get() = R.layout.activity_mywebview //To change initializer of created properties use File | Settings | File Templates.

    override fun init() {
        initWebView()
//        jump.setOnClickListener { jump() }
    }

    private fun initWebView() {
        webView = WebView(this)

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.defaultTextEncodingName = "UTF-8"
        webView.addJavascriptInterface(JavaBridge(this), "android")
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl("file:///android_asset/video.html")
        setContentView(webView)
    }


    private fun jump() {
        val name = account.text
        if (!TextUtils.isEmpty(name)) {
            webView.loadUrl("javascript:javaCallJs('$name')")
            setContentView(webView)
        }
    }


    /*inner class AndroidAndJsInterface {
        */
    /**
     * Js中调用的方法
     */
    /*
            @JavascriptInterface
            fun showToast() {
                Toast.makeText(this@MyWebView, "我是原生代码!", Toast.LENGTH_SHORT).show();
            }

        }*/
}
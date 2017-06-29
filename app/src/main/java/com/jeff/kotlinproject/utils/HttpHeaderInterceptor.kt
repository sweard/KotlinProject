package com.jeff.kotlinproject.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by .F on 2017/5/16.
 * 头部添加
 */

class HttpHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
                .header("User-Agent", "Android, xxx")
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .method(original.method(), original.body())
                .build()
        return chain.proceed(request)
    }
}

package com.jeff.kotlinproject.utils


import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.jeff.kotlinproject.App
import com.rxdemo.jeff.rxdemo.utils.network.ApiService
import okhttp3.Cache
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by jeff on 17-5-15.
 */

class RetrofitClient//初始化
private constructor() {

    var api: ApiService
    private val LOCAL_URL = "http://192.168.2.233/"
    private val BASE_URL = "http://test.mblsoft.com/"

    init {

        // 创建OkHttpClient
        val builder = OkHttpClient.Builder()
                // 超时设置
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                // 错误重连
                .retryOnConnectionFailure(true)
                // 支持HTTPS
                .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)) //明文Http与比较新的Https
                // cookie管理
                .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.instance)))

        // 添加各种插入器
        addInterceptor(builder)

        // 创建Retrofit实例
        val retrofit = Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        // 创建API接口类
        api = retrofit.create(ApiService::class.java)
    }


    private fun addInterceptor(builder: OkHttpClient.Builder) {
        // 添加Header
        builder.addInterceptor(HttpHeaderInterceptor())

        // 添加缓存控制策略
        val cacheDir = App.instance.externalCacheDir
        val cache = Cache(cacheDir!!, (1024 * 1024 * 50).toLong())
        builder.cache(cache).addInterceptor(HttpCacheInterceptor())

        // 添加http log
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logger)

        // 添加调试工具
        //        builder.networkInterceptors().add(new StethoInterceptor());
    }

    companion object {

        var client: RetrofitClient? = null

        //获取单例
        val instance: RetrofitClient
            get() {
                if (client === null) {
                    client = RetrofitClient()
                }
                return client!!
            }
        /* fun instance(): RetrofitClient {
             if (Single.client === null) {
                 Single.client = RetrofitClient()
             }
             return Single.client

         }*/
    }


    object Single {
        lateinit var client: RetrofitClient
    }

}

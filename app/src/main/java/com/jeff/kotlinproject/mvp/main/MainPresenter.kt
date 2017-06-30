package com.jeff.kotlinproject.mvp.main

import com.alibaba.fastjson.JSONObject
import com.jeff.kotlinproject.utils.LogUtils
import com.jeff.kotlinproject.utils.RetrofitClient
import com.jeff.kotlinproject.utils.RxUtils
import com.rxdemo.jeff.rxdemo.utils.network.ApiService
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 * Created by .F on 2017/6/30.
 */
class MainPresenter(val view: MainActivity) : MainContract.Presenter {

    val apiService: ApiService = RetrofitClient.instance().api
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun start() {

    }


    override fun getIMGs() {
        apiService.getBannerIMGs()
                .compose(RxUtils.io_main())
                .subscribeWith(object : DisposableObserver<JSONObject>() {
                    override fun onComplete() {
                        LogUtils.debug("Complete")
                    }

                    override fun onError(e: Throwable?) {
                        LogUtils.debug(e.toString())
                    }

                    override fun onNext(value: JSONObject?) {
                        LogUtils.debug(value?.toJSONString() as String)
                        val jsonArry = value.getJSONArray("body")
                        var imgList: ArrayList<String> = ArrayList()
                        jsonArry.indices
                                .map { jsonArry.getJSONObject(it) }
                                .mapTo(imgList) { "https://pay.mblsoft.com" + it.getString("Config_Value") }
                        LogUtils.debug(imgList[1])
                        view.showIMGs(imgList)
                    }

                })

    }
}
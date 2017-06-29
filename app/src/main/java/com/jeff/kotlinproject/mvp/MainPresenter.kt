package com.jeff.kotlinproject.mvp

import com.alibaba.fastjson.JSONObject
import com.jeff.kotlinproject.utils.LogUtils
import com.jeff.kotlinproject.utils.MD5Util
import com.jeff.kotlinproject.utils.RetrofitClient
import com.rxdemo.jeff.rxdemo.utils.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by jeff on 17-6-29.
 */
class MainPresenter(val view: MainActivity) : MainContract.Presenter {

    var apiService: ApiService ?= null
    var compositeDisposable: CompositeDisposable? = null


    override fun start() {
        apiService = RetrofitClient.instance.api
        compositeDisposable = CompositeDisposable()
    }

    override fun stop() {
        compositeDisposable?.clear()
    }

    override fun login(account: String, password: String) {
        if (account.isEmpty()) {
            view.showDialog("账号不能为空")
        } else if (password.isEmpty()) {
            view.showDialog("密码不能悟空")
        } else {
            val urlcode = MD5Util.getLowerMD5(account + password)!!.substring(8, 24).toString()
            val diaposable = apiService?.login(1, account, urlcode)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribeWith(object : DisposableObserver<JSONObject>() {
                        override fun onError(e: Throwable?) {
                            LogUtils.debug("onError")
                        }

                        override fun onComplete() {
                            LogUtils.debug("onComplete")
                        }

                        override fun onNext(value: JSONObject?) {
                            LogUtils.debug(value?.toJSONString() as String)
                        }

                    })
            compositeDisposable?.add(diaposable)

        }


    }
}
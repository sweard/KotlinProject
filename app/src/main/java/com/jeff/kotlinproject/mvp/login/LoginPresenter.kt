package com.jeff.kotlinproject.mvp.login

import com.alibaba.fastjson.JSONObject
import com.jeff.kotlinproject.utils.LogUtils
import com.jeff.kotlinproject.utils.MD5UtilJava
import com.jeff.kotlinproject.utils.RetrofitClient
import com.rxdemo.jeff.rxdemo.utils.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by jeff on 17-6-29.
 */
class LoginPresenter(val view: LoginActivity) : LoginContract.Presenter {

    lateinit var apiService: ApiService
    lateinit var compositeDisposable: CompositeDisposable


    override fun start() {
        apiService = RetrofitClient.instance().api
        compositeDisposable = CompositeDisposable()
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun login(account: String, password: String) {
        if (account.isEmpty()) {
            view.showDialog("账号不能为空")
        } else if (password.isEmpty()) {
            view.showDialog("密码不能悟空")
        } else {
            val urlcode = MD5UtilJava.getLowerMD5(account + password)!!.substring(8, 24)
            val diaposable = apiService.login(1, account, urlcode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<JSONObject>() {
                        override fun onError(e: Throwable?) {

                            LogUtils.debug(e.toString())
                        }

                        override fun onComplete() {
                            LogUtils.debug("onComplete")
                        }

                        override fun onNext(value: JSONObject?) {
                            LogUtils.debug(value?.toJSONString() as String)
                            if (value.getInteger("code") === 0) {
                                view.toMainAct()
                            } else {
                                LogUtils.debug(value.getInteger("code").toString())
                            }
                        }

                    })
            compositeDisposable.add(diaposable)

        }

    }
}
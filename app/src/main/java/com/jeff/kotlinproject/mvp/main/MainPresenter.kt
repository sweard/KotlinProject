package com.jeff.kotlinproject.mvp.main

import com.alibaba.fastjson.JSONObject
import com.jeff.kotlinproject.utils.LogUtils
import com.jeff.kotlinproject.utils.RetrofitClient
import com.jeff.kotlinproject.utils.RxUtils
import com.rxdemo.jeff.rxdemo.utils.network.ApiService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.realm.Realm

/**
 * Created by .F on 2017/6/30.
 */
class MainPresenter(val view: MainActivity) : MainContract.Presenter {


    override fun add() {
        val realm = Realm.getDefaultInstance()

        realm.beginTransaction()

        var dog = realm.createObject(Dog::class.java,"1")
        dog.age = 5
        dog.name = "jack"

        var dog1 = Dog()
        dog1.age = 3
        dog1.id = "2"
        dog1.name = "peter"
        realm.copyToRealm(dog1)

        realm.commitTransaction()
    }

    override fun delete() {
        val realm = Realm.getDefaultInstance()
        val dogs = realm.where(Dog::class.java).findAll()
        val dog = dogs[0]
        dog.deleteFromRealm()
    }

    override fun change() {
        val realm = Realm.getDefaultInstance()
        var dog = realm.where(Dog::class.java).equalTo("id",0).findFirst()
        realm.beginTransaction()
        dog.name = "change"
        realm.commitTransaction()
    }

    override fun search():List<Dog> {
        val realm = Realm.getDefaultInstance()
        val dogs = realm.where(Dog::class.java).findAll()

        val list = realm.copyFromRealm(dogs)

        return list
    }

    val apiService: ApiService = RetrofitClient.instance().api
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun start() {

    }


    override fun getIMGs() {
        val disposa = apiService.getBannerIMGs()
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

        compositeDisposable.add(disposa)


    }
}
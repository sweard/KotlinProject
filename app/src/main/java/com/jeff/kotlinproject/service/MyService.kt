package com.jeff.kotlinproject.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.jeff.kotlinproject.App
import com.jeff.kotlinproject.utils.DataBaseUtil
import com.jeff.kotlinproject.utils.LogUtils

/**
 * Created by .F on 2017/7/12.
 */
class MyService : Service() {

    lateinit var dbUtil: DataBaseUtil
    lateinit var myBinder: MyBinder

    var autoRun: Boolean = true

    override fun onCreate() {
        super.onCreate()
        myBinder = MyBinder()
        dbUtil = DataBaseUtil(App.instance())
    }

    override fun onBind(p0: Intent?): IBinder {
        return myBinder
    }


    override fun onUnbind(intent: Intent?): Boolean {
        autoRun = false
        return super.onUnbind(intent)
    }

    fun autoAddBook() {
        Thread(Runnable {
            while (autoRun){
                LogUtils.debug("add")
                dbUtil.add()
                Thread.sleep(5000)
            }
        }).start()
    }


    /**
     * 该类用于在onBind方法执行后返回的对象，
     * 该对象对外提供了该服务里的方法
     */
    inner class MyBinder : Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }


}
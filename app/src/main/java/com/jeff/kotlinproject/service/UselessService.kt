package com.jeff.kotlinproject.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.jeff.kotlinproject.utils.LogUtils

/**
 * Created by .F on 2017/7/13.
 */
class UselessService : Service() {

    private var flag: Int = 0

    override fun onCreate() {
        LogUtils.debug("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtils.debug("onStartCommand startId:" + startId)
        flag = startId
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder {

        return UselessBinder()
    }


    inner class UselessBinder : Binder()
}
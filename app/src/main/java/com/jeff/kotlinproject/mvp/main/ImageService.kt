package com.jeff.kotlinproject.mvp.main

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by .F on 2017/6/30.
 */
class ImageService : Service() {
    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }


}
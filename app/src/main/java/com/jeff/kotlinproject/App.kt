package com.jeff.kotlinproject

import android.app.Application
import android.content.Context

/**
 * Created by jeff on 17-6-7.
 */
class App : Application() {

    object Single {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        Single.context = applicationContext
    }


    companion object {
        fun instance(): Context {
            return Single.context
        }
    }

}
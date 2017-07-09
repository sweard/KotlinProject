package com.jeff.kotlinproject

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

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
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }


    companion object {
        fun instance(): Context {
            return Single.context
        }
    }

}
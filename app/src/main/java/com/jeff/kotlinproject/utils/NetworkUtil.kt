package com.jeff.kotlinproject.utils

import android.content.Context
import android.net.ConnectivityManager
import com.jeff.kotlinproject.App

/**
 * Created by .F on 2017/5/16.
 */

object NetworkUtil {

    val isNetworkConnected: Boolean get() {
            val connMgr = App.instance().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connMgr.activeNetworkInfo
            return networkInfo.isConnected
        }

}

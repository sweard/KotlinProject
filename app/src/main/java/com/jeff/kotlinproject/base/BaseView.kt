package com.jeff.kotlinproject.base

import android.widget.Toast

/**
 * Created by jeff on 17-6-29.
 */
interface BaseView {

    fun showDialog(msg: String)

    fun showToast(msg: String): Toast

    fun showProgressDialog(msg: String, flag: Boolean)

}
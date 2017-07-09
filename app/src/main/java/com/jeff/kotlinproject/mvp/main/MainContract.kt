package com.jeff.kotlinproject.mvp.main

import com.jeff.kotlinproject.base.BasePresenter

/**
 * Created by .F on 2017/6/30.
 */
interface MainContract {

    interface View {
        fun showIMGs(list:ArrayList<String>)
    }

    interface Presenter:BasePresenter {
        fun getIMGs()

        fun add()

        fun delete()

        fun change()

        fun search():List<Dog>
    }
}
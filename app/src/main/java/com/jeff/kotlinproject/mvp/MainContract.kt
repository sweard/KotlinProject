package com.jeff.kotlinproject.mvp

import com.jeff.kotlinproject.base.BasePresenter
import com.jeff.kotlinproject.base.BaseView

/**
 * Created by jeff on 17-6-29.
 */
interface MainContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter {
        fun login(account: String, password: String)
    }

}
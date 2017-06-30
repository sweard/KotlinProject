package com.jeff.kotlinproject.mvp.login

import com.jeff.kotlinproject.base.BasePresenter
import com.jeff.kotlinproject.base.BaseView

/**
 * Created by jeff on 17-6-29.
 */
interface LoginContract {

    interface View : BaseView {
        fun toMainAct()
    }

    interface Presenter : BasePresenter {
        fun login(account: String, password: String)
    }

}
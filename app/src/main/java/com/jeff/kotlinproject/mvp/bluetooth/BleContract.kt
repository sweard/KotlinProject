package com.jeff.kotlinproject.mvp.bluetooth

/**
 * Created by jeff on 17-8-11.
 */
interface BleContract {

    interface View {
        fun showToast(msg: String)
        fun enableBle()
    }

    interface Presenter {
        fun checkBle():Boolean

        fun startScanBle()
    }
}
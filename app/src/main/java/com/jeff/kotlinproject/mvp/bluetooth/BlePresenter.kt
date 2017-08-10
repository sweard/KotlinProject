package com.jeff.kotlinproject.mvp.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Build

/**
 * Created by jeff on 17-8-11.
 */
class BlePresenter(activity: BleActivity) : BleContract.Presenter {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private var context: Context = activity
    private var view: BleContract.View = activity

    override fun checkBle(): Boolean {
        initBle()
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter.isEnabled) {
                view.enableBle()
                return false
            } else {
                view.showToast("bluetooth is enabled")
                return true
            }
        } else {
            view.showToast("does not support bluetooth")
            return false
        }
    }

    private fun initBle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            val bluetoothManager: BluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            bluetoothAdapter = bluetoothManager.adapter
        } else {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        }
    }

}
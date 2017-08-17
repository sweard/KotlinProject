package com.jeff.kotlinproject.mvp.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Build
import android.os.Handler
import com.jeff.kotlinproject.utils.LogUtils

/**
 * Created by jeff on 17-8-11.
 */
class BlePresenter(activity: BleActivity) : BleContract.Presenter {

    private var bluetoothAdapter: BluetoothAdapter? = null
    private var deviceList: ArrayList<BluetoothDevice> = ArrayList()

    private var context: Context = activity
    private var view: BleContract.View = activity

    override fun checkBle(): Boolean {
        initBle()
        return if (bluetoothAdapter != null) {
            if (!bluetoothAdapter?.isEnabled!!) {
                view.enableBle()
                false
            } else {
                view.showToast("bluetooth is enabled")
                true
            }
        } else {
            view.showToast("does not support bluetooth")
            false
        }
    }

    override fun startScanBle() {
        val bleCallback = BluetoothAdapter.LeScanCallback { device, _, _ ->
            if (!deviceList.contains(device)) {
                deviceList.add(device)
                LogUtils.debug(device.address)
                LogUtils.debug("addDevice")
            }
        }

        bluetoothAdapter?.startLeScan(bleCallback)

        Handler().postDelayed({
            bluetoothAdapter?.stopLeScan(bleCallback)
        }, 5000)
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
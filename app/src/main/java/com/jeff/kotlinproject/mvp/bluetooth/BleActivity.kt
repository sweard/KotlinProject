package com.jeff.kotlinproject.mvp.bluetooth

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_ble.*
import org.jetbrains.anko.act


/**
 * Created by jeff on 17-8-10.
 */
class BleActivity : BaseActivity(), BleContract.View, View.OnClickListener {

    private lateinit var presenter: BleContract.Presenter
    private val REQUEST_ENABLE = 1
    private var bleStatus = false

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.checkble -> bleStatus = presenter.checkBle()
            R.id.scanble -> {

            }

        }
    }

    override val layoutId: Int
        get() = R.layout.activity_ble //To change initializer of created properties use File | Settings | File Templates.

    override fun init() {
        presenter = BlePresenter(this)

        checkble.setOnClickListener(this)
        scanble.setOnClickListener(this)
    }


    override fun enableBle() {
        val mIntent = Intent("android.bluetooth.adapter.action.REQUEST_ENABLE")
        act.startActivityForResult(mIntent, REQUEST_ENABLE)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE
                && resultCode == Activity.RESULT_OK) bleStatus = true
    }
}
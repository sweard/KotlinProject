package com.jeff.kotlinproject.mvp.login

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.View
import android.widget.Toast
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.base.BaseActivity
import com.jeff.kotlinproject.mvp.main.MainActivity
import com.jeff.kotlinproject.service.MyService
import com.jeff.kotlinproject.utils.LogUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

/**
 * Created by jeff on 17-5-23.
 */
class LoginActivity : BaseActivity(), LoginContract.View, View.OnClickListener {

    lateinit var presenter: LoginPresenter
    var mToast: Toast? = null
    var service: MyService? = null
    var mBind: Boolean = false


    override fun toMainAct() {
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login -> {
                presenter.login(account.text.toString(), password.text.toString())
                LogUtils.debug("login")
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.activity_login


    override fun init() {
        presenter = LoginPresenter(this)

        login.setOnClickListener(this)
        account.setOnClickListener(this)
        password.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
        bindService()
    }

    override fun showToast(msg: String): Toast {
        if (mToast === null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
            mToast?.show()
        } else {
            mToast?.setText(msg)
            mToast?.show()
        }
        return mToast!!
    }

    override fun showProgressDialog(msg: String, flag: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDialog(msg: String) {
        alert(msg) {
            title = "标题"
            yesButton { }

            noButton { }
        }.show()
    }

    override fun onPause() {
        super.onPause()
        presenter.stop()
    }

    val mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            mBind = false
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as MyService.MyBinder
            service = binder.getService()
            mBind = true
            service?.autoAddBook()
            LogUtils.debug("serviceConnected")
        }

    }

    private fun bindService() {
        val intent = Intent(this, MyService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        LogUtils.debug("bindService")
    }

    private fun unBindService() {
        if (mBind) {
            unbindService(mConnection)
            LogUtils.debug("unBind")
            mBind = false
        }
    }


    override fun onStop() {
        super.onStop()
        unBindService()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
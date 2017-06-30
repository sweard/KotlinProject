package com.jeff.kotlinproject.mvp.login

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.base.BaseActivity
import com.jeff.kotlinproject.mvp.main.MainActivity
import com.jeff.kotlinproject.utils.LogUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

/**
 * Created by jeff on 17-5-23.
 */
class LoginActivity : BaseActivity(), LoginContract.View, View.OnClickListener {

    override fun toMainAct() {
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }

    lateinit var presenter: LoginPresenter
    var mToast: Toast? = null

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

}
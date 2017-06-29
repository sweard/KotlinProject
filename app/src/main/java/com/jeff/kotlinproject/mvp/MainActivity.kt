package com.jeff.kotlinproject.mvp

import android.view.View
import android.widget.Toast
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_ankotest.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

/**
 * Created by jeff on 17-5-23.
 */
class MainActivity : BaseActivity(), MainContract.View, View.OnClickListener {

    lateinit var presenter: MainPresenter
    var mToast: Toast? = null

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login -> presenter.login(account.text.toString(), password.text.toString())

            else -> showDialog("hahahah")
        }
    }

    override val layoutId: Int
        get() = R.layout.activity_ankotest


    override fun init() {
        presenter = MainPresenter(this)

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
            yesButton { showToast("OK") }

            noButton { toast("NO") }
        }.show()
    }

}
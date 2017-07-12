package com.jeff.kotlinproject.mvp.main

import android.util.Base64
import android.util.Log
import android.view.View
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.base.BaseActivity
import com.jeff.kotlinproject.utils.AESUtils
import com.jeff.kotlinproject.utils.DataBaseUtil
import com.jeff.kotlinproject.utils.LogUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by .F on 2017/6/30.
 */
class MainActivity : BaseActivity(), MainContract.View, View.OnClickListener {


    lateinit var presenter: MainPresenter
    lateinit var baseUtil: DataBaseUtil

    val key = "1234561234561234"
    val content = "{\"apptype\":\"IOS\",\"appBB\":\"V1.2\",\"action\":\"Get\",\"login\":\"18588258955\",\"token\":\"哇哈哈\",\"body\":{\"name\":\"123\",\"type\":\"1\"}}"

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.add -> baseUtil.add()
            R.id.delete -> baseUtil.delete()
            R.id.change -> baseUtil.updateData()
            R.id.search -> {
                /* val list = presenter.search()
                 for (i in list.indices) Log.e("name", list[i].name)*/
//                baseUtil.query()
                val ivParam = AESUtils.ivParameter()
                LogUtils.debug(ivParam)
                val enStr = AESUtils.encryptAES(content, key, AESUtils.ivParameter())
                LogUtils.debug(enStr)

                var deStr = AESUtils.aesDecryptString(enStr, key, ivParam)
                LogUtils.debug(deStr)

//                presenter.uploadTest()
            }
        }
    }


    override fun showIMGs(list: ArrayList<String>) {
        bannerContainer.setImageLoader(BannerLoader())
        bannerContainer.setImages(list)
        bannerContainer.start()
        bannerContainer.setOnBannerListener { position -> LogUtils.debug(position.toString()) }
    }


    override val layoutId: Int
        get() = R.layout.activity_main


    override fun init() {
        presenter = MainPresenter(this)
        baseUtil = DataBaseUtil(this)

        presenter.getIMGs()

        LogUtils.debug("init()")
        add.setOnClickListener(this)
        delete.setOnClickListener(this)
        change.setOnClickListener(this)
        search.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }
}
package com.jeff.kotlinproject.mvp.main

import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.base.BaseActivity
import com.jeff.kotlinproject.utils.LogUtils
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by .F on 2017/6/30.
 */
class MainActivity : BaseActivity(), MainContract.View {

    lateinit var presenter: MainPresenter

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

        presenter.getIMGs()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }
}
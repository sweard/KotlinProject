package com.jeff.kotlinproject.mvp.bdmap

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.baidu.mapapi.map.BaiduMap
import com.jeff.kotlinproject.R
import kotlinx.android.synthetic.main.activity_bdmap.*

class BDMapActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBaiduMap: BaiduMap
    private lateinit var presenter: BDMapPresenter

    private var typeTag = true
    private var trafficTag = true
    private var heatMapTag = true

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_type -> {
                presenter.changeMapType()
            }

            R.id.btn_traffic -> {
                presenter.setTrafficMap()
            }

            R.id.btn_hotmap -> {
                presenter.setHeatMap()
            }

            R.id.btn_marker -> {
                presenter.setMarker()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bdmap)

        mBaiduMap = bdmap.map

        presenter = BDMapPresenter(mBaiduMap)

        btn_type.setOnClickListener(this)
        btn_traffic.setOnClickListener(this)
        btn_hotmap.setOnClickListener(this)
        btn_marker.setOnClickListener(this)
    }


    override fun onPause() {
        super.onPause()
        bdmap.onPause()
    }

    override fun onResume() {
        super.onResume()
        bdmap.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        bdmap.onDestroy()
    }
}

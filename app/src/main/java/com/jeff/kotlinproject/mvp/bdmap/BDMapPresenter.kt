package com.jeff.kotlinproject.mvp.bdmap

import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.BitmapDescriptorFactory
import com.baidu.mapapi.map.Marker
import com.baidu.mapapi.map.MarkerOptions
import com.baidu.mapapi.model.LatLng
import com.jeff.kotlinproject.R

/**
 * Created by jeff on 17-8-8.
 */
class BDMapPresenter(var mBaiduMap: BaiduMap) : BDMapContract.Presenter {

    private var typeTag = true
    private var trafficTag = true
    private var heatMapTag = true

    private var marker: Marker? = null

    override fun changeMapType() {
        if (typeTag) {
            mBaiduMap.mapType = BaiduMap.MAP_TYPE_SATELLITE
        } else {
            mBaiduMap.mapType = BaiduMap.MAP_TYPE_NORMAL
        }
        typeTag = !typeTag
    }

    override fun setTrafficMap() {
        mBaiduMap.isTrafficEnabled = trafficTag
        trafficTag = !trafficTag
    }

    override fun setHeatMap() {
        mBaiduMap.isBaiduHeatMapEnabled = heatMapTag
        heatMapTag = !heatMapTag
    }

    override fun setMarker() {
        if (marker == null) {
            val point = LatLng(39.963175, 116.400244)
            val bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round)
            val option = MarkerOptions().position(point).icon(bitmap).zIndex(9).draggable(true)

            marker = mBaiduMap.addOverlay(option) as Marker?
       /*     mBaiduMap.setOnMarkerDragListener(object : BaiduMap.OnMarkerDragListener {
                override fun onMarkerDragEnd(p0: Marker?) {
                    LogUtils.debug("onMarkerDragEnd")
                }

                override fun onMarkerDragStart(p0: Marker?) {
                    LogUtils.debug("onMarkerDragStart")
                }

                override fun onMarkerDrag(p0: Marker?) {
                    LogUtils.debug("onMarkerDrag")
                }

            })*/
        } else {
            marker?.remove()
            marker = null
        }

    }


}
package com.jeff.kotlinproject.mvp.bdmap

/**
 * Created by jeff on 17-8-8.
 */
interface BDMapContract {

    interface Presenter {
        fun changeMapType()
        fun setTrafficMap()
        fun setHeatMap()
        fun setMarker()
    }

}
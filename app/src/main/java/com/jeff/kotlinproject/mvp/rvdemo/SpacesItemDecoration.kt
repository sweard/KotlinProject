package com.jeff.kotlinproject.mvp.rvdemo

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View

/**
 * Created by .F on 2017/7/13.
 */
class SpacesItemDecoration(val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect?.left=space
        outRect?.right=space
        outRect?.bottom=space
        if(parent?.layoutManager is GridLayoutManager || parent?.layoutManager is StaggeredGridLayoutManager){
            if(parent?.getChildAdapterPosition(view) < 2){
                outRect?.top=space
            }
        }else{
            if(parent?.getChildAdapterPosition(view)==0){
                outRect?.top=space
            }
        }
    }

}
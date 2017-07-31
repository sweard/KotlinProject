package com.jeff.kotlinproject.mvp.rvdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.graphics.Paint.Align
import android.graphics.Color.DKGRAY
import android.graphics.Paint
import android.text.TextPaint
import android.opengl.ETC1.getWidth
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.utils.LogUtils


/**
 * Created by .F on 2017/7/13.
 */
class SpacesItemDecoration(val space: Int, val context: Context, val callback: DecorationCallback) : RecyclerView.ItemDecoration() {

    var textPaint = TextPaint()
    var paint = Paint()
    var fontMetrics = Paint.FontMetrics()
    val topGap = 100

    init {

        paint = Paint()
        paint.color = R.color.colorPrimary

        //设置悬浮栏中文本的画笔
        textPaint.isAntiAlias = true
        textPaint.textSize = 36f
        textPaint.color = R.color.colorAccent
        textPaint.textAlign = Paint.Align.LEFT
    }


    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
        outRect?.left = space
        outRect?.right = space
        outRect?.bottom = space
        if (parent?.layoutManager is GridLayoutManager || parent?.layoutManager is StaggeredGridLayoutManager) {
            if (parent.getChildAdapterPosition(view) < 2) {
                outRect?.top = space
            }
        } else {
            if (parent?.getChildAdapterPosition(view) == 0) {
                outRect?.top = space
            }

            if (!callback.getTitle(parent.getChildAdapterPosition(view)).equals("")) {
                outRect?.top = space * 2
            }
        }
    }


    override fun onDraw(c: Canvas?, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount - 1
        for (i in 0..childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            LogUtils.error(position.toString())
            if (!callback.getTitle(position).equals("")) {
                val top = view.top - space * 3
                val bottom = view.top
                c?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
                c?.drawText(callback.getTitle(position), left.toFloat() + 100, bottom.toFloat()-10, textPaint)
            }
        }
    }

    interface DecorationCallback {
        fun getTitle(position: Int): String
    }
}


package com.jeff.kotlinproject.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

/**
 * Created by jeff on 17-6-6.
 */
abstract class BaseFragment : Fragment(), View.OnTouchListener {

    /**
     * 防止fragmeng点击穿透
     */
    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (activity.currentFocus != null && activity.currentFocus!!.windowToken != null) {
                manager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        return true
    }

    /**
     * 布局ID
     */
    protected abstract val layoutId: Int

    /**
     * 关联布局文件
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(layoutId, container, false)
        return view
    }

    /**
     * 初始化
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setOnTouchListener(this)
        init()
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 接收数据
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDatas(arguments)
    }

    protected fun init() {}

    protected fun getDatas(arguments: Bundle) {}


}
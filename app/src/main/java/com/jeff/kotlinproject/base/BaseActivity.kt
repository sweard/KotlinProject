package com.jeff.kotlinproject.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager

/**
 * Created by jeff on 17-6-6.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        init()
    }

    /**
     * 关联布局
     */
    protected abstract val layoutId: Int

    /**
     * 初始化在这里
     */
    protected open fun init() {}


    /**
     * 点击空白处隐藏软键盘
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {

        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (currentFocus != null && currentFocus!!.windowToken != null) {
                manager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        return super.onTouchEvent(event)
    }
}
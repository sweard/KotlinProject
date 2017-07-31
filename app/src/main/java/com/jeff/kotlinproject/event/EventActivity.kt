package com.jeff.kotlinproject.event

import android.widget.Toast
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by .F on 2017/7/17.
 */
class EventActivity : BaseActivity() {


    override val layoutId: Int
        get() = R.layout.activity_main //To change initializer of created properties use File | Settings | File Templates.


    override fun onStart() {
        EventBus.getDefault().register(this)
        super.onStart()
    }

    override fun init() {
        add.setOnClickListener{
           Thread().run {
               EventBus.getDefault().post(MessageEvent("Hello"))
           }
        }
    }

    @Subscribe
    fun onEvent(event: MessageEvent) {
        Toast.makeText(this, event.msg, Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }
}
package com.jeff.kotlinproject.mvp.rvdemo

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.base.BaseActivity
import com.jeff.kotlinproject.utils.LogUtils
import kotlinx.android.synthetic.main.activity_recycler.*
import java.util.*

/**
 * Created by .F on 2017/7/13.
 */
class RecyclerViewContainer : BaseActivity() {


    override val layoutId: Int
        get() = R.layout.activity_recycler //To change initializer of created properties use File | Settings | File Templates.


    override fun init() {
        val rd = arrayOf("头布局", "单布局", "多图")
        val title = arrayOf("Title", "", "")
        var list: ArrayList<News> = ArrayList()
        for (i in 0..15) {
            val random = Random()
            val k = random.nextInt(3)
            val news = News(rd[1], title[k])
            LogUtils.debug(news.type as String)
            list.add(news)
        }

        LogUtils.debug(list.size.toString())

        rv.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(list, this)
        rv.adapter = adapter
        rv.addItemDecoration(SpacesItemDecoration(24, this, object : SpacesItemDecoration.DecorationCallback {
            override fun getTitle(position: Int): String {
                return list[position].title
            }

        }))
        adapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {
            override fun onItemLongClick(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean {
                return false
            }

            override fun onItemClick(view: View, holder: RecyclerView.ViewHolder, position: Int) {
                LogUtils.debug(position.toString())
            }

        })

    }

}
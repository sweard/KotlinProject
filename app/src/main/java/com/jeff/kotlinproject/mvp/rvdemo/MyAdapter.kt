package com.jeff.kotlinproject.mvp.rvdemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.utils.LogUtils

/**
 * Created by .F on 2017/7/13.
 */
class MyAdapter(val list: ArrayList<News>, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //新闻模式
    private val TYPE_SINGLE = 0
    //图集模式
    private val TYPE_MULTI = 1
    //头布局模式
    private val TYPE_HEADER = 2

    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context) //To change body of created functions use File | Settings | File Templates.
        var holder: RecyclerView.ViewHolder? = null
        when (viewType) {
            TYPE_MULTI -> {
                val view = inflater.inflate(R.layout.item_multi, parent, false)
                holder = MultiViewHolder(view)
            }

            TYPE_SINGLE -> {
                val view = inflater.inflate(R.layout.item_single, parent, false)
                holder = SingleViewHolder(view)
            }

            TYPE_HEADER -> {
                val view = inflater.inflate(R.layout.item_header, parent, false)
                holder = HeaderViewHolder(view)
            }
        }
        setListener(holder!!)
        return holder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is SingleViewHolder) {
            holder.tv?.text = "Single"
        } else if (holder is MultiViewHolder) {
            holder.tv1?.text = "Text_One"
            holder.tv2?.text = "Text_Two"
            holder.tv3?.text = "Text_Three"
        } else if (holder is HeaderViewHolder) {
            holder.iv?.setImageResource(R.mipmap.ic_launcher)
            LogUtils.debug("setImage")
        }
    }

    override fun getItemCount(): Int {
        return list.size//To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemViewType(position: Int): Int {
        val news = list[position]
        val type = news.type
        when (type) {
            "头布局" -> return TYPE_HEADER
            "单布局" -> return TYPE_SINGLE
            "多图" -> return TYPE_MULTI
            else -> return TYPE_MULTI
        }
    }


    private fun setListener(holder: RecyclerView.ViewHolder) {
        holder.itemView.setOnClickListener { v ->
            val postion = holder.adapterPosition
            itemClickListener?.onItemClick(v as View, holder, postion)
        }

        holder.itemView.setOnLongClickListener { v ->
            val postion = holder.adapterPosition
            itemClickListener?.onItemLongClick(v as View, holder, postion)
            false
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, holder: RecyclerView.ViewHolder, position: Int)

        fun onItemLongClick(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean
    }

    inner class SingleViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView? = null

        init {
            tv = itemView?.findViewById(R.id.single) as TextView?
        }

    }

    inner class MultiViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tv1: TextView? = null
        var tv2: TextView? = null
        var tv3: TextView? = null

        init {
            tv1 = itemView?.findViewById(R.id.one) as TextView?
            tv2 = itemView?.findViewById(R.id.two) as TextView?
            tv3 = itemView?.findViewById(R.id.three) as TextView?
        }


    }

    inner class HeaderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var iv: ImageView? = null

        init {
            iv = itemView?.findViewById(R.id.iv) as ImageView?
        }
    }

}
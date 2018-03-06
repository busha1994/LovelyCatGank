package com.dudu.project.lovelycatgank.ui.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.dudu.project.lovelycatgank.http.bean.GankInfo
import com.dudu.project.lovelycatgank.R
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by admin on 2018/3/4.
 */
class GankInfoAdapter (var context: Context, dates: Array<GankInfo> ?= null) : RecyclerView.Adapter<GankInfoAdapter.Holder>() {

    private var onItemClickListener : OnItemClickListener? = null


    var infos: Array<GankInfo>? = null
        set(value) {
            field = value
            if (currSelect == null && value != null) {
                currSelect = value[0]
                lastPosition = 0
            }
            notifyDataSetChanged()
        }
    private var currSelect: GankInfo? = null
    private var lastPosition: Int = 0

    interface OnItemClickListener {
        fun onclick(v:View,position:Int)
    }
    fun setOnItemClickListener (onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }
    init {
        this.context = context
        this.infos = dates
    }

    override fun onBindViewHolder(holder: GankInfoAdapter.Holder?, position: Int) {
        val info = infos?.get(position)
        if (info != null && holder is Holder) {
            holder.publishTime.text = info.publishedTime.toString()
            holder.articleTitle.text = info.desc
            holder.fromWho.text = info.who
//            holder.infoCover.setImageURI(Uri.parse(info.url))
            if (onItemClickListener != null) {
                holder.root.setOnClickListener {
                    onItemClickListener?.onclick(holder.root, position)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return infos?.size ?: 0
    }

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): Holder? {
        val holder = Holder(LayoutInflater.from(context).inflate(R.layout.item_gankinfo, p0, false))
        return holder
    }

    class Holder(val root: View) : RecyclerView.ViewHolder(root) {
        val publishTime: TextView
        val fromWho:TextView
        val infoCover:SimpleDraweeView
        val articleTitle:TextView

        init {
            publishTime = root.findViewById(R.id.tv_publish_time)
            fromWho = root.findViewById(R.id.tv_afford_name)
            infoCover = root.findViewById(R.id.sd_article_cover)
            articleTitle = root.findViewById(R.id.tv_article_title)
        }
    }
}
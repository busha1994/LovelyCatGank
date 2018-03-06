package com.dudu.project.lovelycatgank.activitys

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dudu.project.lovelycatgank.R
import com.dudu.project.lovelycatgank.http.bean.GankInfo
import com.dudu.project.lovelycatgank.http.bean.GankInfoList
import com.dudu.project.lovelycatgank.http.bean.HttpClient
import com.dudu.project.lovelycatgank.ui.adapter.GankInfoAdapter
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import kotlin.properties.Delegates

/**
 * Created by dudu on 2018/3/6.
 */
class GankItemInfoFragment : Fragment() {
    var array : Array<GankInfo> ?= null
    var adapter : GankInfoAdapter by Delegates.notNull<GankInfoAdapter>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_gankinfo_item,null)
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GankInfoAdapter(activity,array)
        val rvGankList : RecyclerView = view!!.findViewById(R.id.rv_ganklist)
        rvGankList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        rvGankList.adapter = adapter
        adapter.setOnItemClickListener(object : GankInfoAdapter.OnItemClickListener {
            override fun onclick(v: View, position: Int) {
            }
        })
        val textview : TextView = view!!.findViewById(R.id.hello)
        textview.setOnClickListener(View.OnClickListener { loaddata() })
        textview.visibility = View.GONE
        loaddata()
    }

    fun loaddata(){
        val map = mapOf<String,String>()
        val gson = Gson()

        doAsync {
            val stringBuffer = HttpClient.httpGet("http://gank.io/api/random/data/Android/20",map,"1")
            val newsResponse = gson.fromJson(stringBuffer, GankInfoList::class.java)

            var datas = newsResponse
            if (datas == null) {
                uiThread {
//                    toast("数据为空")
                }
            } else {
                uiThread {
                    adapter.infos = datas.results
                }
            }
        }

    }
}
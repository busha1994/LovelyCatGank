package com.dudu.project.lovelycatgank.activitys

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dudu.project.lovelycatgank.R
import com.dudu.project.lovelycatgank.http.bean.GankInfo
import com.dudu.project.lovelycatgank.http.bean.GankInfoList
import com.dudu.project.lovelycatgank.http.bean.HttpClient
import com.dudu.project.lovelycatgank.http.bean.PageParams
import com.dudu.project.lovelycatgank.ui.adapter.GankInfoAdapter
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.properties.Delegates

/**
 * Created by dudu on 2018/3/6.
 */
class GankItemInfoFragment : Fragment() {
    val array : ArrayList<GankInfo> ?= null
    var adapter : GankInfoAdapter by Delegates.notNull<GankInfoAdapter>()
    var pageParams : PageParams = PageParams(1)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_gankinfo_item,null)
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GankInfoAdapter(activity,array)
        val rvGankList : RecyclerView = view!!.findViewById(R.id.swipeToLoad)
        rvGankList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        rvGankList.adapter = adapter
        adapter.setOnItemClickListener(object : GankInfoAdapter.OnItemClickListener {
            override fun onclick(v: View, position: Int) {
            }
        })
        loaddata(1,true)
    }

    fun loaddata(curPage : Int, isFirst : Boolean){
        if (isFirst) {
            pageParams.resetParams()
        } else {
            pageParams.nextPage()
        }
        val gson = Gson()
        doAsync {
            val stringBuffer = HttpClient.httpGet(arguments.getString("infoType"),pageParams.getPage())
            val newsResponse = gson.fromJson(stringBuffer, GankInfoList::class.java)

            var datas = newsResponse
            if (datas == null) {
                uiThread {
                    Toast.makeText(activity,"数据为空",Toast.LENGTH_LONG).show()
                }
            } else {
                uiThread {
                    adapter.infos = refreshList(isFirst, datas.results!!)
                }
            }
        }

    }

    fun refreshList(isFirst : Boolean,arrays :ArrayList<GankInfo>) : ArrayList<GankInfo> {
        if (isFirst) {
            array!!.clear()
        }
        array!!.addAll(arrays)
      return  array
    }
}
package com.dudu.project.lovelycatgank

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.dudu.project.lovelycatgank.http.bean.GankInfo
import com.dudu.project.lovelycatgank.http.bean.GankInfoList
import com.dudu.project.lovelycatgank.http.bean.HttpClient
import com.dudu.project.lovelycatgank.ui.adapter.GankInfoAdapter
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    var array : Array<GankInfo> ?= null
    var adapter : GankInfoAdapter by Delegates.notNull<GankInfoAdapter>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         adapter = GankInfoAdapter(this,array)
        val rvGankList : RecyclerView = findViewById(R.id.rv_ganklist)
        rvGankList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvGankList.adapter = adapter
        adapter.setOnItemClickListener(object : GankInfoAdapter.OnItemClickListener {
            override fun onclick(v: View, position: Int) {
            }
        })
        val textview : TextView = findViewById(R.id.hello)
        textview.setOnClickListener(View.OnClickListener { loaddata() })
//        loaddata()

    }

   fun loaddata(){
       val map = mapOf<String,String>()
       val gson = Gson()
       val handler:Handler




       doAsync {
           val stringBuffer = HttpClient.httpGet("http://gank.io/api/random/data/Android/20",map,"1")
           val newsResponse = gson.fromJson(stringBuffer,GankInfoList::class.java)

           var datas = newsResponse
           if (datas == null) {
               uiThread {
                   toast("数据为空")
               }
           } else {
               uiThread {
                   adapter.infos = datas.results
               }
           }
       }

    }
}

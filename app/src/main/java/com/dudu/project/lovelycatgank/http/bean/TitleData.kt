package com.dudu.project.lovelycatgank.http.bean

/**
 * Created by dudu on 2018/3/6.
 */
class TitleData {
    fun getTitles() : ArrayList<String> {
        val titles = ArrayList<String>()

        titles.clear()
        titles.add("Android")
        titles.add("IOS")
        titles.add("前端")
        titles.add("App")
        titles.add("福利")
        titles.add("瞎推荐")
        titles.add("休息视频")
        titles.add("拓展资源")
        titles.add("全部")
        return titles
    }


}
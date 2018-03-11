package com.dudu.project.lovelycatgank.http.bean

/**
 * Created by dudu on 2018/3/6.
 */
class TitleData {
    fun getTitles() : ArrayList<String> {
        val titles = ArrayList<String>()

        titles.clear()
        titles.add(Const.GankInfoType.ANDROID)
        titles.add(Const.GankInfoType.IOS)
        titles.add(Const.GankInfoType.FRONT_END)
        titles.add(Const.GankInfoType.APP)
        titles.add(Const.GankInfoType.BENIFET)
        titles.add(Const.GankInfoType.RECOMMAND)
        titles.add(Const.GankInfoType.REST_VIDEO)
        titles.add(Const.GankInfoType.EXPAND_RESOURCE)
        titles.add(Const.GankInfoType.ALL)
        return titles
    }


}
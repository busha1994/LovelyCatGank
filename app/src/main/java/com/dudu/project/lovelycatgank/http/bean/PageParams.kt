package com.dudu.project.lovelycatgank.http.bean

/**
 * Created by dudu on 2018/3/10.
 */
class PageParams constructor(var iPages: Int) {
    init {
          var iPageInit = iPages
    }
    private var iViewNumber: Int = 0
    private var iPage = iPages

    fun getViewNumber(): Int {
        return iViewNumber
    }

    fun setViewNumber(iViewNumber: Int) {
        this.iViewNumber = iViewNumber
    }

    fun getPage(): Int {
        if (iPage < 1) {
            iPage = 1
        }
        return iPage
    }

    fun setPage(iPage: Int) {
        this.iPage = iPage
    }

    fun nextPage() {
        this.iPage = this.iPage + 1
    }

    fun resetParams() {
        this.iPage = 1
    }

    fun revertPageIndex() {
        if (this.iPage == 1) {
            return
        }
        this.iPage = this.iPage - 1

    }

}
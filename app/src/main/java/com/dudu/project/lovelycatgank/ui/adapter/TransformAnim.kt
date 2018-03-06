package com.dudu.project.lovelycatgank.ui.adapter

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dudu.project.lovelycatgank.R

/**
 * Created by dudu on 2018/3/6.
 */
class TransformAnim : ViewPager.PageTransformer {

    /**
     * 回调方法,重写viewpager的切换动画
     */
    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width
        val wallpaper = view.findViewById(R.id.rv_ganklist) as RecyclerView
        if (position < -1) { // [-Infinity,-1)
            wallpaper.translationX = 0.toFloat()
            view.translationX = 0.toFloat()
        } else if (position <= 1) { // [-1,1]
            wallpaper.translationX = pageWidth * getFactor(position)
            view.translationX = 8 * position
        } else { // (1,+Infinity]
            wallpaper.translationX = 0.toFloat()
            view.translationX = 0.toFloat()
        }
    }

    private fun getFactor(position: Float): Float {
        return -position / 2
    }

}
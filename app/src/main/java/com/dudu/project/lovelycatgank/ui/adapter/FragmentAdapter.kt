package com.dudu.project.lovelycatgank.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by dudu on 2018/3/6.
 */
class FragmentAdapter(fm: FragmentManager, val mFragments: ArrayList<Fragment>,
                      val mTitles: List<String>)
    : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position]
    }
}
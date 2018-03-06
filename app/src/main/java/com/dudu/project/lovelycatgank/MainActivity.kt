package com.dudu.project.lovelycatgank

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.dudu.project.lovelycatgank.activitys.GankItemInfoFragment
import com.dudu.project.lovelycatgank.http.bean.TitleData
import com.dudu.project.lovelycatgank.ui.adapter.FragmentAdapter
import com.dudu.project.lovelycatgank.ui.adapter.TransformAnim

class MainActivity : AppCompatActivity() {
    var isOk = true
    var mTabLayout: TabLayout? = null
    var mViewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewpager()

    }

    fun initViewpager(): Unit {
        val titles = TitleData().getTitles()
        mViewPager = findViewById(R.id.viewpager) as ViewPager
        mTabLayout = findViewById(R.id.tabLayout) as TabLayout
        for (i in titles.indices) {
            mTabLayout!!.addTab(mTabLayout!!.newTab().setText(titles.get(i)))
        }
        val fragments = ArrayList<Fragment>()
        for (i in titles.indices) {
            val itemFragment = GankItemInfoFragment()
            val bundle = Bundle()
            val stringBuffer = StringBuffer()
            for (j in 1..9) {
                stringBuffer.append(titles[i]).append(" ")
            }
            bundle.putString("content", stringBuffer.toString())
            itemFragment.arguments = bundle
            fragments.add(itemFragment)
        }

        val mFragmentAdapter = FragmentAdapter(supportFragmentManager, fragments, titles)
        mViewPager!!.adapter = mFragmentAdapter
        mViewPager!!.adapter = mFragmentAdapter
        mTabLayout!!.setupWithViewPager(mViewPager)
        mTabLayout!!.setTabsFromPagerAdapter(mFragmentAdapter)
        mViewPager!!.setPageTransformer(true, TransformAnim())
        mTabLayout!!.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                /**
                 * 控制变量
                 */
                if (isOk) {
                    isOk = false
                    val currentItemIndex = mViewPager!!.currentItem

                    if (Math.abs(currentItemIndex - tab!!.position) > 1) {
                        /**
                         * 向后点击
                         */
                        if (currentItemIndex <= tab!!.position) {
                            mViewPager!!.setCurrentItem(tab.position - 1, false)
                            mViewPager!!.setCurrentItem(tab.position, true)
                        }
                        /**
                         * 向前点击
                         */
                        else {
                            mViewPager!!.setCurrentItem(tab.position + 1, false)
                            mViewPager!!.setCurrentItem(tab.position, true)
                        }
                    } else {
                        mViewPager!!.setCurrentItem(tab.position, true)
                    }

                    isOk = true
                }
            }
        })

    }
}

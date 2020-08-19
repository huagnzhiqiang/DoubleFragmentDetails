package com.xiaoqiang.demo.method3

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


/**
 * @author 小强
 *
 * @time 2020/8/19  15:01
 *
 * @desc ViewPager适配器
 *
 */
class AdapterFragment(fm: FragmentManager, mFragments: ArrayList<Fragment>) : FragmentStatePagerAdapter(fm) {

    private val mFragments: List<Fragment> = mFragments


    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun isViewFromObject(view: View, ob: Any): Boolean {
        return (ob as Fragment).view == view
    }

}

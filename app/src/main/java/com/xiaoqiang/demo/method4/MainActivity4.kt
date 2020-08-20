package com.xiaoqiang.demo.method4

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.xiaoqiang.demo.R
import com.xiaoqiang.demo.method2.BottomFragment2
import com.xiaoqiang.demo.method3.AdapterFragment
import kotlinx.android.synthetic.main.activity_main_4.*

/**
 * @author 小强
 *
 * @time 2020/8/19  17:37
 *
 * @desc 利用ViewPager滑动页面
 *
 */

class MainActivity4 : FragmentActivity() {

    private val mFragments : ArrayList<Fragment>? = arrayListOf()

    private var mBottomFragment : BottomFragment2? = null


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_4)

        mBottomFragment = BottomFragment2()
        mFragments?.add(TopFragment4())
        mFragments?.add(mBottomFragment !!)

        mViewPager.adapter = AdapterFragment(supportFragmentManager, mFragments !!)
        mViewPager.offscreenPageLimit = mFragments.size

        //顶部的监听
        mBottomFragment?.setContinueSlideScrollView {
            mViewPager.currentItem = 0
        }

    }

}
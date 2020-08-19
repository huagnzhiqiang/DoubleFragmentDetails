package com.xiaoqiang.demo.method3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiaoqiang.demo.R
import com.xiaoqiang.demo.method1.BottomFragment
import com.xiaoqiang.demo.method1.TopFragment

/**
 * @author 小强
 *
 * @time 2020/8/19  10:33
 *
 * @desc 第二种,利用Fragment切换
 *
 */
class MainFragment2 : Fragment() {

    private var mTopFragment: TopFragment? = null
    private var mBottomFragment: BottomFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTopFragment = childFragmentManager.findFragmentById(R.id.top_fragment) as TopFragment?

        mBottomFragment = childFragmentManager.findFragmentById(R.id.bottom_fragment) as BottomFragment?

        initEvent()

    }


    /**
     * 设置监听
     */
    private fun initEvent() {

        //底部的监听
        mTopFragment?.setContinueSlideScrollView {

            if (mBottomFragment?.isAdded == true) {
                childFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_below_in, R.anim.slide_below_out)
                    .show(mBottomFragment!!).commit()
            }

            mTopFragment?.let {
                childFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_above_in, R.anim.slide_above_out)
                    .hide(it).commit()
            }
        }

        //顶部的监听
        mBottomFragment?.setContinueSlideScrollView {
            if (mTopFragment?.isAdded == true) {
                childFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_above_in, R.anim.slide_above_out)
                    .show(mTopFragment!!).commit()
            }
            mBottomFragment?.let {
                childFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_below_in, R.anim.slide_below_out)
                    .hide(it).commit()
            }
        }

    }

}
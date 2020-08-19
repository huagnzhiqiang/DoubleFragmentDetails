package com.xiaoqiang.demo.method1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiaoqiang.demo.R

/**
 * @author 小强
 *
 * @time 2020/8/19  10:33
 *
 * @desc 第一种,直接在布局里面写入顶部和底部布局
 *
 */
class MainActivity : AppCompatActivity() {

    private var mTopFragment : TopFragment? = null
    private var mBottomFragment : BottomFragment? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val supportFragmentManager = supportFragmentManager
        mTopFragment = supportFragmentManager.findFragmentById(R.id.top_fragment) as TopFragment?
        mBottomFragment = supportFragmentManager.findFragmentById(R.id.bottom_fragment) as BottomFragment?

        initEvent()
    }

    /**
     * 设置监听
     */
    private fun initEvent() {

        //底部的监听
        mTopFragment?.setContinueSlideScrollView {
            if (mBottomFragment?.isAdded == true) {
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_below_in, R.anim.slide_below_out)
                        .show(mBottomFragment !!).commit()
            }

            mTopFragment?.let {
                supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_above_in, R.anim.slide_above_out)
                        .hide(it).commit()
            }
        }

        //顶部的监听
        mBottomFragment?.setContinueSlideScrollView {
            if (mTopFragment?.isAdded == true) {
                supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_above_in, R.anim.slide_above_out)
                        .show(mTopFragment !!).commit()
            }
            mBottomFragment?.let {
                supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_below_in, R.anim.slide_below_out)
                        .hide(it).commit()
            }
        }

    }

}
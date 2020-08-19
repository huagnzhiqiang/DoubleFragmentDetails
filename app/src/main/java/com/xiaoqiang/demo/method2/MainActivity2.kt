package com.xiaoqiang.demo.method2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiaoqiang.demo.R

/**
 * @author 小强
 *
 * @time 2020/8/19  10:33
 *
 * @desc 第二种,利用Fragment切换
 *
 */
class MainActivity2 : AppCompatActivity() {



    //顶部布局
    private val mTopFragment : TopFragment2? by lazy {
        TopFragment2()
    }

    //底部布局
    private val mBottomFragment : BottomFragment2? by lazy {
        BottomFragment2()
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)
        initEvent()
        mTopFragment?.let { supportFragmentManager.beginTransaction().add(R.id.fl, it).commit() }
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
            } else {
                mBottomFragment?.let {
                    supportFragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.slide_below_in, R.anim.slide_below_out)
                            .add(R.id.fl, it).commit()
                }
            }
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_above_in, R.anim.slide_above_out)
                    .hide(mTopFragment !!).commit()
        }

        //顶部的监听
        mBottomFragment?.setContinueSlideScrollView {
            if (mTopFragment?.isAdded == true) {
                mTopFragment?.let {
                    supportFragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.slide_above_in, R.anim.slide_above_out)
                            .show(it).commit()
                }
            } else {
                mTopFragment?.let {
                    supportFragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.slide_above_in, R.anim.slide_above_out)
                            .add(R.id.fl, it).commit()
                }
            }
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_below_in, R.anim.slide_below_out)
                    .hide(mBottomFragment !!).commit()
        }

    }

}
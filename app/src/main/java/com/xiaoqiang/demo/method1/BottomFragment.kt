package com.xiaoqiang.demo.method1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiaoqiang.demo.R
import kotlinx.android.synthetic.main.fragment_bottom.mScrollView

/**
 * @author 小强
 *
 * @time 2020/8/19  10:33
 *
 * @desc 低部的碎片
 *
 */
class BottomFragment : Fragment() {


    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_bottom, container, false)
    }

    /**
     * 滑动监听
     * @param onTopCall 顶部回调
     */
    fun setContinueSlideScrollView(onTopCall : (() -> Unit)? = null) {
        mScrollView?.setonContinueSlideListener(onTopCall)
    }

}
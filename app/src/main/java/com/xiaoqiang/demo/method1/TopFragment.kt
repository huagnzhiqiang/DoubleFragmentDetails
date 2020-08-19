package com.xiaoqiang.demo.method1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiaoqiang.demo.R
import kotlinx.android.synthetic.main.fragment_top.*

/**
 * @author 小强
 *
 * @time 2020/8/19  10:33
 *
 * @desc 顶部的碎片
 *
 */
class TopFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    /**
     * 滑动监听
     * @param onBottomCall 底部回调
     */
    fun setContinueSlideScrollView(onBottomCall: (() -> Unit)? = null) {
        mScrollView?.setonContinueSlideListener(onBottomCall = onBottomCall)
    }

}
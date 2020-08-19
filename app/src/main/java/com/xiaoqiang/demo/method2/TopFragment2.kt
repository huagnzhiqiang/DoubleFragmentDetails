package com.xiaoqiang.demo.method2

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
class TopFragment2 : Fragment() {


    private var onBottomCall : (() -> Unit)? = null

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }


    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mScrollView?.setonContinueSlideListener(onBottomCall = { onBottomCall?.invoke() })
    }

    /**
     * 滑动监听
     * @param bottomCall 底部回调
     */
    fun setContinueSlideScrollView(bottomCall : (() -> Unit)? = null) {
        this.onBottomCall = bottomCall
        Log.e("hzq", "mScrollVie--->$mScrollView")
    }

}
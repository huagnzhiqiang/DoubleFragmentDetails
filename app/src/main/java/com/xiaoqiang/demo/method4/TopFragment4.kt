package com.xiaoqiang.demo.method4

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
class TopFragment4 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_4, container, false)
    }

}
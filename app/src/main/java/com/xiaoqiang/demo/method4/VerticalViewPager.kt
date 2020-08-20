package com.xiaoqiang.demo.method4

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


/**
 * @author 小强
 *
 * @time 2020/8/19  17:43
 *
 * @desc 垂直滑动的ViewPager
 *
 */


class VerticalViewPager(context: Context, attrs: AttributeSet?) :ViewPager(context, attrs) {
    constructor(context: Context) : this(context, null) {}

    init {
        //设置viewPager的切换动画,这里设置才能真正实现垂直滑动的viewpager
        setPageTransformer(true, DefaultTransformer())
    }


    /**
     * 拦截touch事件
     *
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercept = super.onInterceptTouchEvent(swapEvent(ev))
        swapEvent(ev)
        return intercept
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(swapEvent(ev))
    }

    private fun swapEvent(event: MotionEvent): MotionEvent {
        //获取宽高
        val width = width.toFloat()
        val height = height.toFloat()
        //将Y轴的移动距离转变成X轴的移动距离
        val swappedX = event.y / height * width
        //将X轴的移动距离转变成Y轴的移动距离
        val swappedY = event.x / width * height
        //重设event的位置
        event.setLocation(swappedX, swappedY)
        return event
    }


}

package com.xiaoqiang.demo.method4

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


/**
 * @author 小强
 *
 * @time 2020/8/19  17:44
 *
 * @desc 垂直滑动的页面设置
 *
 */
class DefaultTransformer : PageTransformer {
    override fun transformPage(view: View, position: Float) {
        var alpha = 0f
        if (position in 0.0..1.0) {
            alpha = 1 - position
        } else if (-1 < position && position < 0) {
            alpha = position + 1
        }
        view.alpha = alpha
        val transX: Float = view.width * -position
        view.translationX = transX
        val transY: Float = position * view.height
        view.translationY = transY
    }

}
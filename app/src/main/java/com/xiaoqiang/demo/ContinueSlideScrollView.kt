package com.xiaoqiang.demo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ScrollView

/**
 * @author 小强
 *
 * @time 2020/8/19  10:35
 *
 * @desc
 *
 */
class ContinueSlideScrollView : ScrollView {


    //顶部回调
    private var mOnTopCall : (() -> Unit)? = null

    //底部回调
    private var mOnBottomCall : (() -> Unit)? = null

    private var isScrolledToTop = true // 初始化的时候设置一下值

    private var isScrolledToBottom = false

    private var mSmartScrollChangedListener : ISmartScrollChangedListener? = null


    constructor(context : Context) : this(context, null)

    constructor(context : Context, attrs : AttributeSet?) : this(context, attrs, 0)

    constructor(context : Context, attrs : AttributeSet?, defStyleAttr : Int) : super(context, attrs, defStyleAttr) {
    }

    /**
     * 滑动到顶部或底部的监听接口
     */
    interface ISmartScrollChangedListener {
        fun onScrolledToBottom()
        fun onScrolledToTop()
    }

    fun setScanScrollChangedListener(smartScrollChangedListener : ISmartScrollChangedListener?) {
        mSmartScrollChangedListener = smartScrollChangedListener
    }

    override fun onOverScrolled(scrollX : Int, scrollY : Int, clampedX : Boolean, clampedY : Boolean) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY)
        if (scrollY == 0) {
            isScrolledToTop = clampedY
            isScrolledToBottom = false
        } else {
            isScrolledToTop = false
            isScrolledToBottom = clampedY
        }
        notifyScrollChangedListeners()
    }

    //滑动监听
    override fun onScrollChanged(l : Int, t : Int, oldl : Int, oldt : Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        when {
            scrollY == 0 -> {
                isScrolledToTop = true
                isScrolledToBottom = false
            }
            scrollY + height - paddingTop - paddingBottom == getChildAt(0).height -> {
                isScrolledToTop = false
                isScrolledToBottom = true
            }
            else -> {
                isScrolledToTop = false
                isScrolledToBottom = false
            }
        }
        notifyScrollChangedListeners()
    }

    /**
     * 监听回调设置
     */
    private fun notifyScrollChangedListeners() {
        if (isScrolledToTop) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener?.onScrolledToTop()
            }
        } else if (isScrolledToBottom) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener?.onScrolledToBottom()
            }
        }
    }

    //已经滑动到顶部
    private fun isScrolledToTop() : Boolean {
        return isScrolledToTop
    }

    //已经滑动到底部
    private fun isScrolledToBottom() : Boolean {
        return isScrolledToBottom
    }

    /**
     * 继续滑动的代码
     */
    private var down = 0f
    private var move = 0f
    private var isExecute = false //是否要执行
    private var TRIGGER_DISTANCE = 100 //继续滑动效果触发间距 dp

    /**
     * 触摸监听
     * @param ev MotionEvent
     * @return Boolean
     */
    override fun onTouchEvent(ev : MotionEvent) : Boolean {
        when (ev.action) {

            //按下去
            MotionEvent.ACTION_DOWN -> {
                down = ev.y
                isExecute = true
            }

            //移动
            MotionEvent.ACTION_MOVE -> {
                move = ev.y

                if (isExecute && isScrolledToTop() && move - down > dp2px(context, TRIGGER_DISTANCE)) {
                    isExecute = false
                    mOnTopCall?.invoke()
                    Log.e("hzq", "onTouchEvent: 回到顶部，继续滑动" + (move - down))
                }

                if (isExecute && isScrolledToBottom() && move - down < - dp2px(context, TRIGGER_DISTANCE)) {
                    isExecute = false
                    mOnBottomCall?.invoke()
                    Log.e("hzq", "onTouchEvent: 已经到底，继续滑动" + (move - down))
                }
            }

            //抬起

            MotionEvent.ACTION_UP -> {
                isExecute = true
                move = 0f
                down = 0f
            }
        }
        return super.onTouchEvent(ev)
    }



    /**
     * 滑动到顶部或底部继续滑动到一定距离触发
     * @param onTopCall 顶部回调
     * @param onBottomCall 底部回调
     */
    fun setonContinueSlideListener(onTopCall : (() -> Unit)? = null, onBottomCall : (() -> Unit)? = null) {
        this.mOnTopCall = onTopCall
        this.mOnBottomCall = onBottomCall
    }


    companion object {
        /**
         * dip转换px
         */
        fun dp2px(content : Context, dip : Int) : Int {
            val scale = content.resources.displayMetrics.density
            return (dip * scale + 0.5f).toInt()
        }
    }
}
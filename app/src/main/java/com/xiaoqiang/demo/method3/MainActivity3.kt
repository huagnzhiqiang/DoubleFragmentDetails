package com.xiaoqiang.demo.method3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.viewpager.widget.ViewPager
import com.xiaoqiang.demo.R
import com.xiaoqiang.demo.method1.BottomFragment
import com.xiaoqiang.demo.method2.BottomFragment2
import com.xiaoqiang.demo.method2.TopFragment2
import kotlinx.android.synthetic.main.activity_main_3.*

/**
 * @author 小强
 *
 * @time 2020/8/19  10:33
 *
 * @desc 第一种,直接在布局里面写入顶部和底部布局
 *
 */
class MainActivity3 : FragmentActivity() {

    private val mFragments: ArrayList<Fragment>? = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_3)

        (0 until 10).map {
            mFragments?.add(MainFragment2())
        }

        mViewPager.offscreenPageLimit = 12
        mViewPager.adapter = AdapterFragment(supportFragmentManager, mFragments!!)

    }

}
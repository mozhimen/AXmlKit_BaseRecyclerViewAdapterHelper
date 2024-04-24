package com.chad.library.adapter3.test.anim.commons

import android.view.View

/**
 * @ClassName IExpandable
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/4/24
 * @Version 1.0
 */
interface IExpandable {
    fun getExpandView(): View?

    fun onAnimStart(isOpen: Boolean)
}
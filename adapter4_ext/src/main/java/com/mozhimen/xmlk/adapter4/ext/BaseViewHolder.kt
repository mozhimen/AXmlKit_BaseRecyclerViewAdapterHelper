package com.mozhimen.xmlk.adapter4.ext

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.chad.library.adapter4.viewholder.QuickViewHolder

/**
 * @ClassName BaseViewHolder
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/4/26
 * @Version 1.0
 */
open class BaseViewHolder : QuickViewHolder {
    constructor(view: View) : super(view)

    constructor(parent: ViewGroup, @LayoutRes intResLayout: Int) : super(parent, intResLayout)
}
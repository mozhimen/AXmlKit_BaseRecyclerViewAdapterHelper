package com.mozhimen.xmlk.adapter4.ext

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import com.chad.library.adapter4.BaseMultiItemAdapter
import com.chad.library.adapter4.util.getItemView
import com.mozhimen.basick.utilk.android.util.UtilKLogWrapper
import com.mozhimen.basick.utilk.commons.IUtilK

/**
 * @ClassName BaseItemProvider
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/4/26
 * @Version 1.0
 */
abstract class BaseItemProvider<T : Any> : IUtilK, BaseMultiItemAdapter.OnMultiItem<T, BaseViewHolder>() {

    abstract val layoutId: Int
        @LayoutRes
        get

    ///////////////////////////////////////////////////////////////////////

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(parent.getItemView(layoutId))
    }

    @CallSuper
    override fun onBindViewHolder(holder: BaseViewHolder, item: T?, position: Int) {
        UtilKLogWrapper.v(TAG, "onBindViewHolder:         $holder position $position item $item")
        holder.onBind()
    }

    @CallSuper
    override fun onViewAttachedToWindow(holder: BaseViewHolder, item: T?, position: Int?) {
        UtilKLogWrapper.v(TAG, "onViewAttachedToWindow:   $holder position $position item $item")
        holder.onViewAttachedToWindow()
    }

    @CallSuper
    override fun onViewDetachedFromWindow(holder: BaseViewHolder, item: T?, position: Int?) {
        UtilKLogWrapper.v(TAG, "onViewDetachedFromWindow: $holder position $position item $item")
        holder.onViewDetachedFromWindow()
    }

    override fun onViewRecycled(holder: BaseViewHolder, item: T?, position: Int?) {
        UtilKLogWrapper.v(TAG, "onViewRecycled:           $holder position $position item $item")
        holder.onViewRecycled()
    }
}
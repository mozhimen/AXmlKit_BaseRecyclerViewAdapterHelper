package com.chad.library.adapter3.test.bundle.items

import android.annotation.SuppressLint
import android.nfc.Tag
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter3.provider.BaseItemProvider
import com.chad.library.adapter3.test.R
import com.chad.library.adapter3.test.bundle.mos.SItem
import com.chad.library.adapter3.viewholder.BaseViewHolder
import com.mozhimen.basick.elemk.androidx.recyclerview.OnScrollListenerIdleImpl
import com.mozhimen.basick.taskk.handler.TaskKHandler
import com.mozhimen.basick.utilk.android.util.UtilKLogWrapper
import com.mozhimen.basick.utilk.android.widget.showToast
import java.lang.ref.WeakReference

/**
 * @ClassName HeaderProvider
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/3/15
 * @Version 1.0
 */
class HeaderProvider : BaseItemProvider<SItem>() {
    override val itemViewType: Int
        get() = 3
    override val layoutId: Int
        get() = R.layout.item_normal

    private fun next(tag: String, pos: Int, holder: BaseViewHolder) {
        com.mozhimen.basick.utilk.android.util.UtilKLogWrapper.d(TAG, "tag $tag 停止了滑动 $pos holder $holder")
        "tag $tag 停止了滑动 $pos".showToast()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BaseViewHolder, item: SItem, position: Int) {
        super.onBindViewHolder(holder, item, position)
        if (item is SItem.Header) {
            val txt = holder.findViewById<TextView>(R.id.item_txt)

            val runnable = Runnable {
                getProviderMultiAdapter()?.recyclerView?.apply {
                    addOnScrollListener(OnScrollListenerIdleImpl(WeakReference(getProviderMultiAdapter()?.recyclerView)) {
                        next("addOnScrollListener", position, holder)
                    }.also { txt.setTag("listener".hashCode(), it) })
                } ?: run {
                    next("null", position, holder)
                }
            }
            txt.apply {
                setTag("runnable".hashCode(), runnable)
                text = "Header"
            }
            TaskKHandler.postDelayed(3000L, runnable)
        }
    }

    override fun onViewRecycled(holder: BaseViewHolder, item: SItem?, position: Int?) {
        val txt = holder.findViewById<TextView>(R.id.item_txt)
        val runnable = txt.getTag("runnable".hashCode()) as? Runnable?
        val listener = txt.getTag("listener".hashCode()) as? OnScrollListenerIdleImpl?
        runnable?.let {
            UtilKLogWrapper.d(TAG,"removeCallbacks")
            TaskKHandler.get().removeCallbacks(it)
        }
        listener?.let {
            UtilKLogWrapper.d(TAG,"removeOnScrollListener")
            getProviderMultiAdapter()?.recyclerView?.removeOnScrollListener(it)
        }
        super.onViewRecycled(holder, item, position)
    }
}
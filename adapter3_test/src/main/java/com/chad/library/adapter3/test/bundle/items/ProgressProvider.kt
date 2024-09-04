package com.chad.library.adapter3.test.bundle.items

import android.widget.ProgressBar
import android.widget.TextView
import com.chad.library.adapter3.provider.BaseItemProvider
import com.chad.library.adapter3.test.R
import com.chad.library.adapter3.test.bundle.mos.SEvent
import com.chad.library.adapter3.test.bundle.mos.SItem
import com.chad.library.adapter3.viewholder.BaseViewHolder
import com.mozhimen.kotlin.utilk.kotlin.ranges.constraint

/**
 * @ClassName ProgressProvider
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/2/27 0:27
 * @Version 1.0
 */
class ProgressProvider : BaseItemProvider<SItem>() {
    override val itemViewType: Int
        get() = 1
    override val layoutId: Int
        get() = R.layout.item_progress

    override fun onBindViewHolder(holder: BaseViewHolder, item: SItem, position: Int) {
        super.onBindViewHolder(holder, item, position)
        if (item is SItem.Progress) {
            val progress = holder.findViewById<ProgressBar>(R.id.item_progress)
            val txt = holder.findViewById<TextView>(R.id.item_txt)
            progress.progress = item.progress.constraint(0, 100)
            txt.text = item.id.toString()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: SItem, position: Int, payloads: List<Any>) {
        val progress = holder.findViewById<ProgressBar>(R.id.item_progress)
        if (payloads.isNotEmpty()) {
            val flag = payloads[0]
            if (flag is SEvent) {
                when (flag) {
                    is SEvent.Change -> progress.progress = flag.value
                }
            }
        }
    }
}
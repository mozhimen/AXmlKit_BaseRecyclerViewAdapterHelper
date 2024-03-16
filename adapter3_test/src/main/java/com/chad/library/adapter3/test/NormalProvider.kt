package com.chad.library.adapter3.test

import android.widget.TextView
import com.chad.library.adapter3.provider.BaseItemProvider
import com.chad.library.adapter3.viewholder.BaseViewHolder

/**
 * @ClassName NormalProvider
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/3/15
 * @Version 1.0
 */
class NormalProvider : BaseItemProvider<SItem>() {
    override val itemViewType: Int
        get() = 2
    override val layoutId: Int
        get() = R.layout.item_normal

    override fun onBindViewHolder(holder: BaseViewHolder, item: SItem, position: Int?) {
        super.onBindViewHolder(holder, item, position)
        if (item is SItem.Normal) {
            holder.getView<TextView>(R.id.item_txt).text = item.text
        }
    }
}
package com.chad.library.adapter3.test.bundle.items

import android.annotation.SuppressLint
import android.widget.TextView
import com.chad.library.adapter3.provider.BaseItemProvider
import com.chad.library.adapter3.test.R
import com.chad.library.adapter3.test.bundle.mos.SItem
import com.chad.library.adapter3.viewholder.BaseViewHolder

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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BaseViewHolder, item: SItem , position: Int?) {
        super.onBindViewHolder(holder, item, position)
        if (item is SItem.Header) {
            holder.findViewById<TextView>(R.id.item_txt).text = "Header"
        }
    }
}
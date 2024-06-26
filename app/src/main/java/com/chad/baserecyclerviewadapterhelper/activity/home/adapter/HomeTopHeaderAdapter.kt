package com.chad.baserecyclerviewadapterhelper.activity.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.baserecyclerviewadapterhelper.R
import com.chad.library.adapter4.BaseSingleItemAdapter
import com.chad.library.adapter4.fullspan.FullSpanAdapterType
import com.mozhimen.xmlk.adapter4.ext.BaseViewHolder

class HomeTopHeaderAdapter : BaseSingleItemAdapter<Any, HomeTopHeaderAdapter.VH>(),
    FullSpanAdapterType {

    companion object {
        val HEAD_VIEWTYPE = 0x10000556
    }

    class VH(view: View) : BaseViewHolder(view)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.top_view, parent, false))
    }

    override fun onBindViewHolder(holder: VH, item: Any?) {
    }

    override fun getItemViewType(position: Int, list: List<Any>): Int {
        return HEAD_VIEWTYPE
    }


}
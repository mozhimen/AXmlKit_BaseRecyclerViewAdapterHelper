package com.chad.baserecyclerviewadapterhelper.activity.emptyview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.baserecyclerviewadapterhelper.databinding.LayoutAnimationBinding
import com.chad.baserecyclerviewadapterhelper.entity.Status
import com.chad.library.adapter4.BaseQuickAdapter
import com.mozhimen.xmlk.adapter4.ext.BaseViewHolder

class EmptyViewAdapter : BaseQuickAdapter<Status, EmptyViewAdapter.VH>() {

    class VH(
        parent: ViewGroup,
        val binding: LayoutAnimationBinding = LayoutAnimationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : BaseViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int, item: Status?) {
        super.onBindViewHolder(holder, position, item)
        if (item == null) return
        holder.binding.img.setImageResource(item.userAvatar)
        holder.binding.tweetName.text = item.userName
        holder.binding.tweetText.text = "O ever youthful,O ever weeping"
    }

}
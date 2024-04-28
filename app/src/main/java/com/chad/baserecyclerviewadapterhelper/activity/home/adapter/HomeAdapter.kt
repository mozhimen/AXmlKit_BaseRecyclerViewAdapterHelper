package com.chad.baserecyclerviewadapterhelper.activity.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.baserecyclerviewadapterhelper.databinding.DefSectionHeadBinding
import com.chad.baserecyclerviewadapterhelper.databinding.HomeItemViewBinding
import com.chad.baserecyclerviewadapterhelper.entity.HomeEntity
import com.chad.library.adapter4.BaseMultiItemAdapter
import com.mozhimen.xmlk.adapter4.ext.BaseViewHolder

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
class HomeAdapter(data: List<HomeEntity>) : BaseMultiItemAdapter<HomeEntity,BaseViewHolder>(data) {

    class ItemVH(val viewBinding: HomeItemViewBinding) : BaseViewHolder(viewBinding.root)

    class HeaderVH(val viewBinding: DefSectionHeadBinding) : BaseViewHolder(viewBinding.root)

    init {
        addItemType(ITEM_TYPE, object : OnMultiItemAdapterListener<HomeEntity, BaseViewHolder> {
            override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): ItemVH {
                val viewBinding =
                    HomeItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
                return ItemVH(viewBinding)
            }

            override fun onBindViewHolder(holder: BaseViewHolder, item: HomeEntity?, position: Int) {
                if (item == null) return
                if (holder is ItemVH){
                    holder.viewBinding.textView.text = item.name
                    holder.viewBinding.icon.setImageResource(item.imageResource)
                }
            }
        }).addItemType(SECTION_TYPE, object : OnMultiItemAdapterListener<HomeEntity, BaseViewHolder> {
            override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): HeaderVH {
                val viewBinding =
                    DefSectionHeadBinding.inflate(LayoutInflater.from(context), parent, false)
                return HeaderVH(viewBinding)
            }

            override fun onBindViewHolder(holder: BaseViewHolder, item: HomeEntity?, position: Int) {
                if (item == null) return
                if (holder is HeaderVH){
                    holder.viewBinding.more.visibility = View.GONE
                    holder.viewBinding.header.text = item.sectionTitle
                }
            }

            override fun isFullSpanItem(itemType: Int): Boolean {
                return true
            }

        }).onItemViewType { position, list ->
            if (list[position].isSection) {
                SECTION_TYPE
            } else {
                ITEM_TYPE
            }
        }
    }

    companion object {
        private const val ITEM_TYPE = 10
        private const val SECTION_TYPE = 11
    }
}

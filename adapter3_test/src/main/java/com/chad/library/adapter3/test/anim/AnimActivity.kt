package com.chad.library.adapter3.test.anim

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter3.test.R
import com.chad.library.adapter3.test.anim.commons.IExpandable
import com.chad.library.adapter3.test.databinding.ActivityAnimBinding
import com.mozhimen.basick.elemk.androidx.appcompat.bases.databinding.BaseActivityVDB

/**
 * @ClassName AnimActivity
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/4/24
 * @Version 1.0
 */
class AnimActivity : BaseActivityVDB<ActivityAnimBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        ExpandableViewHoldersUtil.getInstance().init().setNeedExplanedOnlyOne(false)

        vdb.mainRecycler.apply {
            layoutManager = LinearLayoutManager(this@AnimActivity)

            //清空记录展开还是关闭的缓存数据
            ExpandableViewHoldersUtil.getInstance().resetExpanedList()

            adapter = MyAdapter()
        }
    }

    inner class MyAdapter : RecyclerView.Adapter<ViewHolder>() {

        var keepOne: ExpandableViewHoldersUtil.KeepOneHolder<ViewHolder>? = ExpandableViewHoldersUtil.getInstance().keepOneHolder

        override fun getItemCount(): Int {
            return 20
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(this@AnimActivity).inflate(R.layout.item_user_concern_layout, parent, false)
            return ViewHolder(view)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvTitle.setText("中美经贸磋商 po=$position")

            keepOne?.bind(holder, position)

            holder.tvTitle.setOnClickListener(
                View.OnClickListener { //                    if(ExpandableViewHoldersUtil.isExpaned(position)){
                    //                        viewHolder.contentTv.setMaxLines(3);
                    //                    }else {
                    //                        viewHolder.contentTv.setMaxLines(100);
                    //                    }
                    keepOne?.toggle(holder)
                })

            holder.lvArrorwBtn.setOnClickListener(
                View.OnClickListener {
                    keepOne?.toggle(holder)
                })
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), IExpandable {

        var tvTitle: TextView = itemView.findViewById<TextView>(R.id.item_user_concern_title)
        var arrowImage: ImageView = itemView.findViewById<ImageView>(R.id.item_user_concern_arrow_image)
        var lvArrorwBtn: LinearLayout = itemView.findViewById<LinearLayout>(R.id.item_user_concern_arrow)
        var lvLinearlayout: LinearLayout = itemView.findViewById<LinearLayout>(R.id.item_user_concern_link_layout)
        var contentTv: TextView = itemView.findViewById<TextView>(R.id.item_user_concern_link_text)

        init {
            lvLinearlayout.visibility = View.GONE
            lvLinearlayout.alpha = 0f
        }

        override fun getExpandView(): View {
            return lvLinearlayout
        }

        override fun onAnimStart(isOpen: Boolean) {
            if (isOpen) {
                ExpandableViewHoldersUtil.getInstance().rotateExpandIcon(arrowImage, 180f, 0f)
            } else {
                ExpandableViewHoldersUtil.getInstance().rotateExpandIcon(arrowImage, 0f, 180f)
            }
        }
    }
}
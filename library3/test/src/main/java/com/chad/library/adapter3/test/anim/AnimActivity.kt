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
import com.mozhimen.basick.animk.builder.mos.MAnimKConfig
import com.mozhimen.basick.animk.builder.utils.AnimKTypeUtil
import com.mozhimen.bindk.bases.activity.databinding.BaseActivityVDB
import com.mozhimen.kotlin.utilk.android.view.applyGone
import com.mozhimen.xmlk.vhk.utils.VHKAnimUtil

/**
 * @ClassName AnimActivity
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/4/24
 * @Version 1.0
 */
class AnimActivity : BaseActivityVDB<ActivityAnimBinding>() {

    private val _expandList: ArrayList<AnimBundle> = ArrayList() //缓存的数据
    private val _isExpandOnlyOne = false
    private var _animDuration: Long = 300

    override fun initView(savedInstanceState: Bundle?) {
        repeat(20) {
            _expandList.add(AnimBundle(false))
        }

        vdb.mainRecycler.apply {
            layoutManager = LinearLayoutManager(this@AnimActivity)
            adapter = AnimRecyclerViewAdapter(_expandList)
        }
    }

    inner class AnimRecyclerViewAdapter(private val _list: List<AnimBundle>) : RecyclerView.Adapter<AnimViewHolder>() {

        private val _panelAnimProxy: PanelAnimProxy by lazy_ofNone { PanelAnimProxy() }

        override fun getItemCount(): Int =
            _list.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimViewHolder =
            AnimViewHolder(LayoutInflater.from(this@AnimActivity).inflate(R.layout.item_user_concern_layout, parent, false))

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: AnimViewHolder, position: Int) {
            holder.tvTitle.setText("中美经贸磋商 po=$position")

            _panelAnimProxy.onBindViewHolder(holder, _list[position].isExpand)

            holder.tvTitle.setOnClickListener {
                _list[position].isExpand = !_list[position].isExpand
                _panelAnimProxy.onGenerateViewHolder(holder, _list[position].isExpand)
            }

            holder.lvArrorwBtn.setOnClickListener {
                _list[position].isExpand = !_list[position].isExpand
                _panelAnimProxy.onGenerateViewHolder(holder, _list[position].isExpand)
            }
        }
    }

    inner class AnimViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), IExpandable {

        var tvTitle: TextView = itemView.findViewById<TextView>(R.id.item_user_concern_title)
        var arrowImage: ImageView = itemView.findViewById<ImageView>(R.id.item_user_concern_arrow_image)
        var lvArrorwBtn: LinearLayout = itemView.findViewById<LinearLayout>(R.id.item_user_concern_arrow)
        var lvLinearlayout: LinearLayout = itemView.findViewById<LinearLayout>(R.id.item_user_concern_link_layout)
        var contentTv: TextView = itemView.findViewById<TextView>(R.id.item_user_concern_link_text)

        init {
            lvLinearlayout.applyGone()
            lvLinearlayout.alpha = 0f
        }

        override fun getExpandView(): View {
            return lvLinearlayout
        }

        override fun onAnimStart(isExpand: Boolean) {
            if (isExpand) {
                AnimKTypeUtil.get_ofRotate(arrowImage, 0f, 180f).build(MAnimKConfig(duration = _animDuration)).start()
                VHKAnimUtil.expandViewHolder(this, this.getExpandView(), true)
            } else {
                AnimKTypeUtil.get_ofRotate(arrowImage, 180f, 0f).build(MAnimKConfig(duration = _animDuration)).start()
                VHKAnimUtil.foldViewHolder(this, getExpandView(), true)
            }
        }
    }

    @Suppress("deprecation")
    inner class PanelAnimProxy {
        var _lastExpandPos: Int = 0

        /**
         * 此方法是在Adapter的onBindViewHolder()方法中调用
         */
        fun onBindViewHolder(holder: AnimViewHolder, isExpand: Boolean) {
            if (isExpand) {
                VHKAnimUtil.expandViewHolder(holder, holder.getExpandView(), false)
            } else {
                VHKAnimUtil.foldViewHolder(holder, holder.getExpandView(), false)
            }
        }

        /**
         * 响应ViewHolder的点击事件
         */
        fun onGenerateViewHolder(holder: AnimViewHolder, isExpand: Boolean) {
            val position = holder.position
            if (isExpand) {
                holder.onAnimStart(true)

                //是否要关闭上一个
                if (_isExpandOnlyOne && _lastExpandPos != position) {
                    ((holder.itemView.parent as RecyclerView).findViewHolderForPosition(_lastExpandPos) as? AnimViewHolder?)?.onAnimStart(false)
                }

                _lastExpandPos = position
            } else {
                holder.onAnimStart(false)
            }
        }
    }

    data class AnimBundle(var isExpand: Boolean)
}
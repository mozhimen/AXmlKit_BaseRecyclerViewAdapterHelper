package com.chad.library.adapter3.test.anim

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.mozhimen.basick.animk.builder.utils.AnimKBuilderUtil
import com.mozhimen.basick.animk.builder.utils.AnimKUtil
import com.mozhimen.basick.elemk.android.animation.BaseViewHolderAnimatorListenerAdapter
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
        vdb.mainRecycler.apply {
            layoutManager = LinearLayoutManager(this@AnimActivity)

            //清空记录展开还是关闭的缓存数据
            resetExpanedList()

            adapter = MyAdapter()
        }
    }

    inner class MyAdapter : RecyclerView.Adapter<ViewHolder>() {

        val keepOne: KeepOneHolder by lazy { KeepOneHolder() }

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

            keepOne.bind(holder, position)

            holder.tvTitle.setOnClickListener(
                View.OnClickListener { //                    if(ExpandableViewHoldersUtil.isExpaned(position)){
                    //                        viewHolder.contentTv.setMaxLines(3);
                    //                    }else {
                    //                        viewHolder.contentTv.setMaxLines(100);
                    //                    }
                    keepOne.toggle(holder)
                })

            holder.lvArrorwBtn.setOnClickListener(
                View.OnClickListener {
                    keepOne.toggle(holder)
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
                AnimKBuilderUtil.get_ofRotate(arrowImage, 180f, 0f).setDuration(animalDuration).build().start()
            } else {
                AnimKBuilderUtil.get_ofRotate(arrowImage, 0f, 180f).setDuration(animalDuration).build().start()
            }
        }
    }

    private val needExplanedOnlyOne = false
    private val explanedList: ArrayList<String> = ArrayList() //缓存的数据

    //-1表示所有item是关闭状态，opend为pos值的表示pos位置的item为展开的状态
    private var opened = -1
    fun resetExpanedList() {
        explanedList.clear()
    }

    @Suppress("deprecation")
    inner class KeepOneHolder {
        var preOpen: Int = 0

        /**
         * 此方法是在Adapter的onBindViewHolder()方法中调用
         * @param holder holder对象
         * @param pos    下标
         */
        fun bind(holder: ViewHolder, pos: Int) {
            if (explanedList.contains(pos.toString() + "")) {
                openHolder(holder, holder!!.getExpandView(), false)
            } else {
                closeHolder(holder, holder!!.getExpandView(), false)
            }
        }

        /**
         * 响应ViewHolder的点击事件
         * @param holder holder对象
         */
        fun toggle(holder: ViewHolder) {
            val position = holder.position
            if (explanedList.contains(position.toString() + "")) {
                opened = -1
                deletePositionInExpaned(position)

                holder.onAnimStart(true)
                closeHolder(holder, holder.getExpandView(), true)
            } else {
                preOpen = opened
                opened = position

                addPositionInExpaned(position)
                holder.onAnimStart(false)
                openHolder(holder, holder.getExpandView(), true)

                //是否要关闭上一个
                if (needExplanedOnlyOne && preOpen != position) {
                    val oldHolder = (holder.itemView.parent as RecyclerView).findViewHolderForPosition(preOpen) as ViewHolder
                    if (oldHolder != null) {
                        Log.e("KeepOneHolder", "oldHolder != null")
                        closeHolder(oldHolder, oldHolder.getExpandView(), true)
                        deletePositionInExpaned(preOpen)
                    }
                }
            }
        }
    }

    var animalDuration: Long = 300
    var alphaDuration: Long = 100

    fun isExpaned(index: Int): Boolean {
        return explanedList.contains(index.toString() + "")
    }

    private fun addPositionInExpaned(pos: Int) {
        if (!explanedList.contains(pos.toString() + "")) {
            explanedList.add(pos.toString() + "")
        }
    }

    private fun deletePositionInExpaned(pos: Int) {
        //remove Object 直接写int，会变成index,造成数组越界
        explanedList.remove(pos.toString() + "")
    }

    //参数介绍：1、holder对象 2、展开部分的View，由holder.getExpandView()方法获取 3、animate参数为true，则有动画效果
    private fun openHolder(holder: RecyclerView.ViewHolder, expandView: View, animate: Boolean) {
        if (animate) {
            expandView.visibility = View.VISIBLE
            //改变高度的动画
            val animator = AnimKUtil.get_ofHeight_viewHolder(holder)

            //扩展的动画，透明度动画开始
            val alphaAnimator = ObjectAnimator.ofFloat(expandView, View.ALPHA, 1f)
            alphaAnimator.setDuration(animalDuration + alphaDuration)
            alphaAnimator.addListener(BaseViewHolderAnimatorListenerAdapter(holder))

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(animator, alphaAnimator)
            animatorSet.start()
        } else { //为false时直接显示
            expandView.visibility = View.VISIBLE
            expandView.alpha = 1f
        }
    }

    //类似于打开的方法
    private fun closeHolder(holder: RecyclerView.ViewHolder, expandView: View, animate: Boolean) {
        if (animate) {
            expandView.visibility = View.GONE
            val animator = AnimKUtil.get_ofHeight_viewHolder(holder)
            expandView.visibility = View.VISIBLE
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    expandView.visibility = View.GONE
                    expandView.alpha = 0f
                }

                override fun onAnimationCancel(animation: Animator) {
                    expandView.visibility = View.GONE
                    expandView.alpha = 0f
                }
            })
            animator.start()
        } else {
            expandView.visibility = View.GONE
            expandView.alpha = 0f
        }
    }
}
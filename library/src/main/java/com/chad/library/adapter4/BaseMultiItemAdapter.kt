package com.chad.library.adapter4

import android.content.Context
import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mozhimen.xmlk.vhk.VHKLifecycle
import java.lang.ref.WeakReference

/**
 * MultiItemType layout.
 * 多类型布局
 *
 */
abstract class BaseMultiItemAdapter<T : Any, VH : VHKLifecycle>(items: List<T> = emptyList()) :
    BaseQuickAdapter<T, VH>(items) {

    private val typeViewHolders =
        SparseArray<OnMultiItemAdapterListener<T, VH>>(1)

    private var onItemViewTypeListener: OnItemViewTypeListener<T>? = null

    override fun onCreateViewHolder(
        context: Context, parent: ViewGroup, viewType: Int
    ): VH {
        val listener = typeViewHolders.get(viewType)
            ?: throw IllegalArgumentException("ViewType: $viewType not found onViewHolderListener，please use addItemType() first!")

        return listener.onCreateViewHolder(parent.context, parent, viewType).apply {
            itemView.setTag(R.id.BaseQuickAdapter_key_multi, listener)
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int, item: T?) {
        super.onBindViewHolder(holder, position, item)
        findListener(holder)?.onBindViewHolder(holder, item, position)
    }

    override fun onBindViewHolder(
        holder: VH, position: Int, item: T?, payloads: List<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position, item)
            return
        }

        findListener(holder)?.onBindViewHolder(holder, item, position, payloads)
    }

    /**
     * Call this function to add multiTypeItems.
     * 调用此方法，设置多布局
     * @param itemViewType Int
     * @param listener Int
     */
    fun addItemType(
        listener: OnMultiItemAdapterListener<T, VH>
    ) = apply {
        if (listener is OnMultiItem) {
            listener.weakA = WeakReference(this)
        }
        typeViewHolders.put(
            listener.itemViewType, listener as OnMultiItemAdapterListener<T, VH>
        )
    }

    /**
     * 设置 ItemViewType 的监听，根据不同数据类型，返回不同的type值
     *
     * @param listener
     */
    fun onItemViewType(listener: OnItemViewTypeListener<T>?) = apply {
        this.onItemViewTypeListener = listener
    }

    override fun getItemViewType(position: Int, list: List<T>): Int {
        return onItemViewTypeListener?.onItemViewType(position, list)
            ?: super.getItemViewType(position, list)
    }

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)

        val position = getPosition(holder)
        findListener(holder)?.onViewAttachedToWindow(holder, position?.let { getItem(it) }, position)
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)

        val position = getPosition(holder)
        findListener(holder)?.onViewDetachedFromWindow(holder, position?.let { getItem(it) }, position)
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)

        val position = getPosition(holder)
        findListener(holder)?.onViewRecycled(holder, position?.let { getItem(it) }, position)
    }

    override fun onFailedToRecycleView(holder: VH): Boolean {
        return findListener(holder)?.onFailedToRecycleView(holder) ?: false
    }

    override fun isFullSpanItem(itemType: Int): Boolean {
        return super.isFullSpanItem(itemType) ||
                (typeViewHolders.get(itemType)?.isFullSpanItem(itemType) == true)
    }

    private fun findListener(holder: RecyclerView.ViewHolder): OnMultiItemAdapterListener<T, VH>? {
        return holder.itemView.getTag(R.id.BaseQuickAdapter_key_multi) as? OnMultiItemAdapterListener<T, VH>
    }

    /**
     * 多类型布局 Adapter Listener
     *
     * @param T 数据类型
     * @param V ViewHolder 类型
     */
    interface OnMultiItemAdapterListener<T, VH> {
        val itemViewType: Int

        fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH

        fun onBindViewHolder(holder: VH, item: T?, position: Int)

        fun onBindViewHolder(holder: VH, item: T?, position: Int, payloads: List<Any>) {
            onBindViewHolder(holder, item, position)
        }

        fun onViewAttachedToWindow(holder: VH, item: T?, position: Int?) {}

        fun onViewDetachedFromWindow(holder: VH, item: T?, position: Int?) {}

        fun onViewRecycled(holder: VH, item: T?, position: Int?) {}

        fun onFailedToRecycleView(holder: VH): Boolean = false

        fun isFullSpanItem(itemType: Int): Boolean {
            return false
        }
    }

    /**
     * 如果需要一些属性，例如：adapter、context，则使用此抽象类.
     * 如果不需要，则可以直接实现[OnMultiItemAdapterListener]接口.
     *
     * @param T
     * @param V
     * @constructor Create empty On multi item
     */
    abstract class OnMultiItem<T : Any, VH : VHKLifecycle> :
        OnMultiItemAdapterListener<T, VH> {
        internal var weakA: WeakReference<BaseMultiItemAdapter<T, VH>>? = null

        val adapter: BaseMultiItemAdapter<T, VH>?
            get() = weakA?.get()

        val context: Context?
            get() = weakA?.get()?.context
    }

    fun interface OnItemViewTypeListener<T> {
        /**
         * 根据不同数据类型，返回不同的type值
         *
         * @param position
         * @param list
         * @return
         */
        fun onItemViewType(position: Int, list: List<T>): Int
    }
}
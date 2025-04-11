package com.mozhimen.xmlk.adapter4.ext.commons

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter4.BaseQuickAdapter
import com.mozhimen.xmlk.adapter4.ext.bases.BaseViewHolder

/**
 * @ClassName ILeleListActivity
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/6/20
 * @Version 1.0
 */
interface IListActivity<DES : Any, VH : BaseViewHolder> {
    fun getViewModel():BaseListViewModel<DES>
    fun getListAdapter(): BaseQuickAdapter<DES, VH>
    fun getSwipeRefreshLayout(): SwipeRefreshLayout?
    fun getSwipeRefreshLayoutColorScheme(): Int = 0
    fun getRecyclerView(): RecyclerView
    fun getRecyclerViewLayoutManager(): RecyclerView.LayoutManager
    fun getRecyclerViewItemDecoration(): RecyclerView.ItemDecoration? = null
    fun onRefresh()
    fun onFirstLoadStart()
    fun onFirstLoadEmpty()
    fun onFirstLoadFinish()
}
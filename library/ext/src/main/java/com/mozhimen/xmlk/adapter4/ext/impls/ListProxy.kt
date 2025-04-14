package com.mozhimen.xmlk.adapter4.ext.impls

import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.xmlk.adapter4.ext.commons.IListActivity
import com.mozhimen.xmlk.adapter4.ext.cons.CListLoadState

/**
 * @ClassName BasePagingKProxy
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/5/25 22:21
 * @Version 1.0
 */
@OApiCall_BindViewLifecycle
@OApiInit_ByLazy
@OApiCall_BindLifecycle
class ListProxy<DES : Any>(private var _listActivity: IListActivity<DES>?) : BaseWakeBefDestroyLifecycleObserver() {

    fun initLayout(owner: LifecycleOwner) {
        val listActivity = _listActivity ?: return
        listActivity.getSwipeRefreshLayout()?.apply {
            if (listActivity.getSwipeRefreshLayoutColorScheme() != 0)
                setColorSchemeResources(listActivity.getSwipeRefreshLayoutColorScheme())
            setOnRefreshListener { listActivity.onRefresh() }
        }
        listActivity.getRecyclerView().apply {
            layoutManager = listActivity.getRecyclerViewLayoutManager()
            listActivity.getRecyclerViewItemDecoration()?.let { addItemDecoration(it) }
            adapter = listActivity.getListAdapter()
        }
        listActivity.getViewModel().liveLoadState.observe(owner) {
            when (it) {
                CListLoadState.STATE_FIRST_LOAD_START -> {
                    listActivity.getSwipeRefreshLayout()?.isRefreshing = true
                    listActivity.onFirstLoadStart()
                }

                CListLoadState.STATE_FIRST_LOAD_FINISH -> {
                    listActivity.getSwipeRefreshLayout()?.isRefreshing = false
                    listActivity.onFirstLoadFinish()
                }

                else -> {
                    listActivity.getSwipeRefreshLayout()?.isRefreshing = false
                    listActivity.onFirstLoadEmpty()
                }
            }
        }
        listActivity.onRefresh()
        listActivity.getViewModel().liveList.observe(owner) {
            listActivity.getListAdapter().submitList(it)
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        _listActivity = null
        super.onDestroy(owner)
    }
}
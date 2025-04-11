package com.mozhimen.xmlk.adapter4.ext.bases

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.utilk.android.view.applyGone
import com.mozhimen.kotlin.utilk.android.view.applyVisible
import com.mozhimen.kotlin.utilk.kotlin.UtilKLazyJVM.lazy_ofNone
import com.mozhimen.uik.databinding.bases.viewdatabinding.activity.BaseActivityVDBVM
import com.mozhimen.xmlk.adapter4.ext.commons.BaseListViewModel
import com.mozhimen.xmlk.adapter4.ext.commons.IListActivity
import com.mozhimen.xmlk.adapter4.ext.impls.ListProxy

/**
 * @ClassName BaseLeleListFragmentVBVM
 * @Description 统计,多语言, Bar
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/11/21 10:27
 * @Version 1.0
 */
abstract class BaseListActivityVDBVM<DES : Any, VH : BaseViewHolder, VB : ViewDataBinding, VM : BaseListViewModel<DES>> : BaseActivityVDBVM<VB, VM>, IListActivity<DES, VH> {

    /**
     * 针对Hilt(@JvmOverloads kotlin默认参数值无效)
     * @constructor
     */
    constructor() : super()

    //////////////////////////////////////////////////////////////////////////////

    @OptIn(OApiInit_ByLazy::class, OApiCall_BindLifecycle::class, OApiCall_BindViewLifecycle::class)
    private val _listProxy by lazy_ofNone { ListProxy<DES, VH>(this).apply { bindLifecycle(this@BaseListActivityVDBVM) } }

    //////////////////////////////////////////////////////////////////////////////

    @OptIn(OApiInit_ByLazy::class, OApiCall_BindLifecycle::class, OApiCall_BindViewLifecycle::class)
    @CallSuper
    override fun initLayout() {
        super.initLayout()
        _listProxy.initLayout(this)
    }

    //////////////////////////////////////////////////////////////////////////////

    override fun getViewModel(): BaseListViewModel<DES> =
        vm

    override fun onRefresh() {
        vm.onRefreshList()
    }

    @CallSuper
    override fun onFirstLoadEmpty() {
        getRecyclerView().applyGone()
    }

    @CallSuper
    override fun onFirstLoadFinish() {
        getRecyclerView().applyVisible()
    }
}
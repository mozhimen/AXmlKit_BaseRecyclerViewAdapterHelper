package com.mozhimen.xmlk.adapter4.ext.bases.uis.viewbinding.fragment

import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.utilk.android.view.applyGone
import com.mozhimen.kotlin.utilk.android.view.applyVisible
import com.mozhimen.kotlin.utilk.kotlin.UtilKLazyJVM.lazy_ofNone
import com.mozhimen.uik.databinding.bases.viewbinding.fragment.BaseDialogFragmentVBVM
import com.mozhimen.xmlk.adapter4.ext.commons.BaseListViewModel
import com.mozhimen.xmlk.adapter4.ext.commons.IListActivity
import com.mozhimen.xmlk.adapter4.ext.impls.ListProxy

/**
 * @ClassName BaseListDialogFragmentVBVM
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2025/4/13 15:39
 * @Version 1.0
 */
@OptIn(OApiInit_ByLazy::class, OApiCall_BindLifecycle::class, OApiCall_BindViewLifecycle::class)
abstract class BaseListDialogFragmentVBVM<DES : Any, VB : ViewBinding, VM : BaseListViewModel<DES>>() : BaseDialogFragmentVBVM<VB, VM>(), IListActivity<DES> {

    private val _listProxy by lazy_ofNone { ListProxy<DES>(this).apply { bindLifecycle(this@BaseListDialogFragmentVBVM) } }

    /////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun initLayout() {
        super.initLayout()
        _listProxy.initLayout(this.viewLifecycleOwner)
    }

    /////////////////////////////////////////////////////////////////////

    override fun getViewModel(): BaseListViewModel<DES> {
        return vm
    }

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
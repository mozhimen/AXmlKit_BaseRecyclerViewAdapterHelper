package com.mozhimen.xmlk.adapter4.ext.bases.uis.viewbinding.activity

import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.utilk.android.view.applyGone
import com.mozhimen.kotlin.utilk.android.view.applyVisible
import com.mozhimen.kotlin.utilk.kotlin.UtilKLazyJVM.lazy_ofNone
import com.mozhimen.uik.databinding.bases.viewbinding.activity.BaseBarActivityVBVM
import com.mozhimen.xmlk.adapter4.ext.commons.BaseListViewModel
import com.mozhimen.xmlk.adapter4.ext.commons.IListActivity
import com.mozhimen.xmlk.adapter4.ext.impls.ListProxy

/**
 * @ClassName BaseListBarActivityVBVM
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/5/20
 * @Version 1.0
 */
@OptIn(OApiInit_ByLazy::class, OApiCall_BindLifecycle::class, OApiCall_BindViewLifecycle::class)
abstract class BaseListBarActivityVBVM<DES : Any, VB : ViewBinding, VM : BaseListViewModel<DES>> : BaseBarActivityVBVM<VB, VM>(), IListActivity<DES> {

    @OptIn(OApiInit_ByLazy::class, OApiCall_BindLifecycle::class, OApiCall_BindViewLifecycle::class)
    private val _listProxy by lazy_ofNone { ListProxy<DES>(this).apply { bindLifecycle(this@BaseListBarActivityVBVM) } }

    /////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun initLayout() {
        super.initLayout()
        _listProxy.apply {
            bindLifecycle(this@BaseListBarActivityVBVM)
            initLayout(this@BaseListBarActivityVBVM)
        }
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
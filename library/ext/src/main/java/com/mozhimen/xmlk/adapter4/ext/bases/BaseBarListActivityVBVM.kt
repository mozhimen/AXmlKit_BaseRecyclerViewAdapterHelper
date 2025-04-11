package com.mozhimen.xmlk.adapter4.ext.bases

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.mozhimen.basick.impls.proxys.IToolbarProxy
import com.mozhimen.basick.impls.proxys.IToolbarProxyProvider
import com.mozhimen.basick.impls.proxys.ToolbarProxy
import com.mozhimen.kotlin.elemk.commons.IA_Listener
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.utilk.android.view.applyGone
import com.mozhimen.kotlin.utilk.android.view.applyVisible
import com.mozhimen.kotlin.utilk.androidx.appcompat.UtilKActionBar
import com.mozhimen.kotlin.utilk.kotlin.UtilKLazyJVM
import com.mozhimen.kotlin.utilk.kotlin.UtilKLazyJVM.lazy_ofNone
import com.mozhimen.uik.databinding.bases.viewbinding.activity.BaseActivityVBVM
import com.mozhimen.xmlk.adapter4.ext.commons.BaseListViewModel
import com.mozhimen.xmlk.adapter4.ext.commons.IListActivity
import com.mozhimen.xmlk.adapter4.ext.impls.ListProxy

/**
 * @ClassName BaseBarPagingKActivityVDBVM
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/5/20
 * @Version 1.0
 */
@OptIn(OApiInit_ByLazy::class, OApiCall_BindLifecycle::class, OApiCall_BindViewLifecycle::class)
abstract class BaseBarListActivityVBVM<DES : Any, VH : BaseViewHolder, VB : ViewBinding, VM : BaseListViewModel<DES>> : BaseActivityVBVM<VB, VM>, IToolbarProxyProvider, IToolbarProxy , IListActivity<DES, VH> {
    /**
     * 针对Hilt(@JvmOverloads kotlin默认参数值无效)
     * @constructor
     */
    constructor() : super()

    /////////////////////////////////////////////////////////////////////

    protected val _toolBarProxy by UtilKLazyJVM.lazy_ofNone { ToolbarProxy<BaseBarListActivityVBVM<*,*, *, *>>().apply { bindLifecycle(this@BaseBarListActivityVBVM) } }
    @OptIn(OApiInit_ByLazy::class, OApiCall_BindLifecycle::class, OApiCall_BindViewLifecycle::class)
    private val _listProxy by lazy_ofNone { ListProxy<DES, VH>(this).apply { bindLifecycle(this@BaseBarListActivityVBVM) } }

    /////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun initLayout() {
        super.initLayout()
        UtilKActionBar.get_ofSupport(this)?.let {
            _toolBarProxy.initToolbar(this, it)
        }
        _listProxy.initLayout(this)
    }

    /////////////////////////////////////////////////////////////////////

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

    /////////////////////////////////////////////////////////////////////

    override fun setToolbarNavigationIconRes(intResDrawable: Int) {
        _toolBarProxy.setToolbarNavigationIconRes(intResDrawable)
    }

    override fun <A> setToolbarNavigationOnClickListener(provider: A, listener: IA_Listener<A>) where A : AppCompatActivity, A : IToolbarProxyProvider {
        _toolBarProxy.setToolbarNavigationOnClickListener(provider, listener)
    }

    override fun setToolbarBackground(drawable: Drawable) {
        _toolBarProxy.setToolbarBackground(drawable)
    }

    override fun setToolbarBackgroundColor(intColor: Int) {
        _toolBarProxy.setToolbarBackgroundColor(intColor)
    }

    override fun setToolbarBackgroundRes(intResDrawable: Int) {
        _toolBarProxy.setToolbarBackgroundRes(intResDrawable)
    }

    override fun setToolbarText(strText: CharSequence) {
        _toolBarProxy.setToolbarText(strText)
    }

    override fun setToolbarTextRes(intStrRes: Int) {
        _toolBarProxy.setToolbarTextRes(intStrRes)
    }

    override fun setToolbarCustomView(customView: View) {
        _toolBarProxy.setToolbarCustomView(customView)
    }
}
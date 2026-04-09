package com.mozhimen.xmlk.adapter4.ext.commons

import androidx.lifecycle.MutableLiveData
import com.mozhimen.kotlin.elemk.androidx.lifecycle.bases.BaseViewModel
import com.mozhimen.xmlk.adapter4.ext.cons.CListLoadState

/**
 * @ClassName BaseLeleListViewModel
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/11/21 9:38
 * @Version 1.0
 */
abstract class BaseListViewModel<DES> : BaseViewModel() {
    val liveList = MutableLiveData<List<DES>>()
    val liveLoadState = MutableLiveData<Int>()

    //////////////////////////////////////////////////////////////////////////////

    /**
     *     override fun onRefreshList() {
     *         viewModelScope.launch(Dispatchers.IO) {
     *             onFirstLoadStart()
     *             val list = _mainRepository.getAppCategoriesOnBack()
     *             if (list.isNotEmpty()) {
     *                 this@CategoriesViewModel.liveList.postValue(list)
     *                 onFirstLoadFinish()
     *             } else {
     *                 this@CategoriesViewModel.liveList.postValue(emptyList())
     *                 onFirstLoadEmpty()
     *             }
     *         }
     *     }
     */
    abstract fun onRefreshList()

    //////////////////////////////////////////////////////////////////////////////

    fun onFirstLoadStart() {
        liveLoadState.postValue(CListLoadState.STATE_FIRST_LOAD_START)
    }

    fun onFirstLoadEmpty() {
        liveLoadState.postValue(CListLoadState.STATE_FIRST_LOAD_EMPTY)
    }

    fun onFirstLoadFinish() {
        liveLoadState.postValue(CListLoadState.STATE_FIRST_LOAD_FINISH)
    }
}
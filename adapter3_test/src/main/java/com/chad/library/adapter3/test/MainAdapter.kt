package com.chad.library.adapter3.test

import com.chad.library.adapter3.BaseProviderMultiAdapter
import com.mozhimen.basick.elemk.androidx.lifecycle.bases.BaseViewModel

/**
 * @ClassName MainAdapter
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/2/27 0:21
 * @Version 1.0
 */
class MainAdapter : BaseProviderMultiAdapter<ProgressBean>() {
    init {
        addItemProvider(ProgressProvider())
    }

    override fun getItemType(data: List<ProgressBean>, position: Int): Int =
        1
}
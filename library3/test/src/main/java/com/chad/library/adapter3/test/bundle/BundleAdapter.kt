package com.chad.library.adapter3.test.bundle

import com.chad.library.adapter3.BaseProviderMultiAdapter
import com.chad.library.adapter3.test.bundle.items.HeaderProvider
import com.chad.library.adapter3.test.bundle.items.NormalProvider
import com.chad.library.adapter3.test.bundle.items.ProgressProvider
import com.chad.library.adapter3.test.bundle.mos.SItem

/**
 * @ClassName MainAdapter
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/2/27 0:21
 * @Version 1.0
 */
class BundleAdapter : BaseProviderMultiAdapter<SItem>() {
    init {
        addItemProvider(ProgressProvider())
        addItemProvider(NormalProvider())
        addItemProvider(HeaderProvider())
    }

    override fun getItemType(data: List<SItem>, position: Int): Int =
        data[position].type
}
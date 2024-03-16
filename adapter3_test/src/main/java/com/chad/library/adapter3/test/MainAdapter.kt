package com.chad.library.adapter3.test

import com.chad.library.adapter3.BaseProviderMultiAdapter

/**
 * @ClassName MainAdapter
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/2/27 0:21
 * @Version 1.0
 */
class MainAdapter : BaseProviderMultiAdapter<SItem>() {
    init {
        addItemProvider(ProgressProvider())
        addItemProvider(NormalProvider())
        addItemProvider(HeaderProvider())
    }

    override fun getItemType(data: List<SItem>, position: Int): Int =
        data[position].type
}
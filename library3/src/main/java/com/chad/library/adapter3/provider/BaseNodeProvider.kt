package com.chad.library.adapter3.provider

import com.chad.library.adapter3.BaseNodeAdapter
import com.chad.library.adapter3.entity.node.BaseNode

abstract class BaseNodeProvider : BaseItemProvider<BaseNode>() {

    override fun getProviderMultiAdapter(): BaseNodeAdapter? {
        return super.getProviderMultiAdapter() as? BaseNodeAdapter
    }

}
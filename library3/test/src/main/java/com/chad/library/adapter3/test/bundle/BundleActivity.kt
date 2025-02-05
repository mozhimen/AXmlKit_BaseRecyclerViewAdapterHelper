package com.chad.library.adapter3.test.bundle

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter3.BaseQuickAdapter
import com.chad.library.adapter3.listener.OnItemChildClickListener
import com.chad.library.adapter3.test.R
import com.chad.library.adapter3.test.bundle.mos.SEvent
import com.chad.library.adapter3.test.bundle.mos.SItem
import com.chad.library.adapter3.test.databinding.ActivityBundleBinding
import com.mozhimen.uik.databinding.bases.activity.databinding.BaseActivityVDB

/**
 * @ClassName BundleActivity
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/4/24
 * @Version 1.0
 */
class BundleActivity : BaseActivityVDB<ActivityBundleBinding>(){
    private val _adapter: BundleAdapter by lazy {
        BundleAdapter().apply {
            addChildClickViewIds(R.id.item_btn)
            setOnItemChildClickListener(object : OnItemChildClickListener<SItem> {
                override fun onItemChildClick(adapter: BaseQuickAdapter<SItem, *>, view: View, position: Int) {
                    val item: SItem = adapter.getItem(position)
                    if (item is SItem.Progress) {
                        item.progress += 10
                        if (item.progress > 100) {
                            item.progress = 0
                        }
                        adapter.setData(position, item, SEvent.Change(item.progress))
                    }
                }
            })
        }
    }

    private val _itemBeans = mutableListOf<SItem>()
    override fun initView(savedInstanceState: Bundle?) {
        vdb.mainRecycler.apply {
            layoutManager = LinearLayoutManager(this@BundleActivity)
            adapter = _adapter
        }

        _itemBeans.add(SItem.Header)
        _itemBeans.add(SItem.Header)
        for (i in 0 until 20) {
            if (i % 2 == 0) {
                _itemBeans.add(SItem.Progress(i, 0))
            } else {
                _itemBeans.add(SItem.Normal(i.toString()))
            }
        }
        _adapter.addData(_itemBeans)
    }
}
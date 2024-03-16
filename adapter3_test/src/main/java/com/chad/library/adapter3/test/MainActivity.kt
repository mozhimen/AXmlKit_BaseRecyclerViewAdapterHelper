package com.chad.library.adapter3.test

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter3.BaseQuickAdapter
import com.chad.library.adapter3.listener.OnItemChildClickListener
import com.chad.library.adapter3.test.databinding.ActivityMainBinding
import com.mozhimen.basick.elemk.androidx.appcompat.bases.databinding.BaseActivityVDB

class MainActivity : BaseActivityVDB<ActivityMainBinding>() {
    private val _adapter: MainAdapter by lazy {
        MainAdapter().apply {
            addChildClickViewIds(R.id.item_btn)
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
            layoutManager = LinearLayoutManager(this@MainActivity)
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
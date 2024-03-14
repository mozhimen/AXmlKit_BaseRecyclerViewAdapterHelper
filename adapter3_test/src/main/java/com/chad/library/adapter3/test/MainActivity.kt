package com.chad.library.adapter3.test

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter3.BaseQuickAdapter
import com.chad.library.adapter3.listener.OnItemChildClickListener
import com.chad.library.adapter3.test.databinding.ActivityMainBinding
import com.mozhimen.basick.elemk.androidx.appcompat.bases.databinding.BaseActivityVDB

class MainActivity : BaseActivityVDB<ActivityMainBinding>() {
    private val _adapter:MainAdapter by lazy {
        MainAdapter().apply {
            addChildClickViewIds(R.id.item_btn)
            setOnItemChildClickListener(object : OnItemChildClickListener<ItemBean> {
                override fun onItemChildClick(adapter: BaseQuickAdapter<ItemBean, *>, view: View, position: Int) {
                    val item: ItemBean = adapter.getItem(position)
                    item.progress += 10
                    if (item.progress > 100) {
                        item.progress = 0
                    }
                    adapter.setData(position, item, EEvent.Change(item.progress))
                }
            })
        }
    }

    private val _itemBeans = mutableListOf<ItemBean>()
    override fun initView(savedInstanceState: Bundle?) {
        vdb.mainRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = _adapter
        }

        for (i in 0 until 20) {
            _itemBeans.add(i, ItemBean(i, 0))
        }
        _adapter.addData(_itemBeans)
    }
}
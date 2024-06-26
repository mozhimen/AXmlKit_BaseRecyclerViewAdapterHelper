package com.chad.library.adapter3.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter3.BaseQuickAdapter;

/**
 * @author: limuyang
 * @date: 2019-12-03
 * @Description:
 */
public interface OnItemChildClickListener<T> {
    /**
     * callback method to be invoked when an item child in this view has been click
     *
     * @param adapter  BaseQuickAdapter
     * @param view     The view whihin the ItemView that was clicked
     * @param position The position of the view int the adapter
     */
    void onItemChildClick(@NonNull BaseQuickAdapter<T,?> adapter, @NonNull View view, int position);
}

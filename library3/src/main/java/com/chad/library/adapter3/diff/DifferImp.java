package com.chad.library.adapter3.diff;

import androidx.annotation.NonNull;

import com.chad.library.adapter3.diff.ListChangeListener;

/**
 * 使用java接口定义方法
 * @param <T>
 */
public interface DifferImp<T> {
    void addListListener(@NonNull ListChangeListener<T> listChangeListener);
}

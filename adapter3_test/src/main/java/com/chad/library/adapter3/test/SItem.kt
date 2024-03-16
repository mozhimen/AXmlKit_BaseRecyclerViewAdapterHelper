package com.chad.library.adapter3.test

/**
 * @ClassName ProgressBean
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/2/27 0:26
 * @Version 1.0
 */
sealed class SItem(val type: Int) {
    data class Progress(var id: Int, var progress: Int) : SItem(1)
    data class Normal(var text: String) : SItem(2)
    data object Header : SItem(3)
}
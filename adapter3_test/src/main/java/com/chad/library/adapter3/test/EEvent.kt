package com.chad.library.adapter3.test

/**
 * @ClassName EEvent
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/2/27 0:48
 * @Version 1.0
 */
sealed class EEvent {
    data class Change(val value: Int) : EEvent()
}
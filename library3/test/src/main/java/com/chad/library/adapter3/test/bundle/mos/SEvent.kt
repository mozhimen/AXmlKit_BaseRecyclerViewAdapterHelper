package com.chad.library.adapter3.test.bundle.mos

/**
 * @ClassName EEvent
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/2/27 0:48
 * @Version 1.0
 */
sealed class SEvent {
    data class Change(val value: Int) : SEvent()
}
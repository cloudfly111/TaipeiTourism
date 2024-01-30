package com.judy.demo.taipei.tourism.repository.dataClass

/** API 最外層架構
 * - T 泛型：可傳入每支 API 的 data 對應的 data class
 * */
data class APIStruct<T>(
    val total:Int,
    val data:List<T>
)



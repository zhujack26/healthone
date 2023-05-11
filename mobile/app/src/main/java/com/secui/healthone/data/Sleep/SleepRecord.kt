package com.secui.healthone.data.Sleep

data class SleepRecord(
    val no: Int? = null,
    val userNo: Int = 1,
    val createTime: String,
    val startSleepTime: String,
    val endSleepTime: String
)

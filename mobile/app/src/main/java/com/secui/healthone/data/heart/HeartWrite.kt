package com.secui.healthone.data.heart

import java.time.LocalDateTime

data class HeartWrite (
    val userNo:Int = 0,
    val createTime:String = LocalDateTime.now().toString(),
    val count:Int = 0
)

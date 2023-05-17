package com.secui.healthone.data

data class HealthInfo(
    val nickname: String,
    val createTime: String,
    val gender: Boolean,
    val birthdate: String,
    val height: Int,
    val weight: Int,
    val workRate: String,
    val stepGoal: Int,
    val sleepTime: String,
    val wakeUpTime: String
)
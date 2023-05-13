package com.secui.healthone.data.HealthStatus

data class HealthStatus(
    val no : Int?,
    val userNo: Int,
    val createTime: String,
    val height: Int,
    val weight: Int,
    val bmi: Int,
    val bodyFatPercentage: Int,
    val skeletalMuscleMass: Int,
    val waistMeasurement: Int,
    val fbg: Int,
    val hdlCholesterol: Int,
    val tg: Int,
    val lowBloodPressure: Int,
    val highBloodPressure: Int
)

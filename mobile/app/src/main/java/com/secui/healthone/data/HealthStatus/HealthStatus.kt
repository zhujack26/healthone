package com.secui.healthone.data.HealthStatus

data class HealthStatus(
    val no : Int? = null,
    val userNo: Int,
    val createTime: String,
    val height: Int? = null,
    val weight: Int? = null,
    val bmi: Int? = null,
    val bodyFatPercentage: Int? = null,
    val skeletalMuscleMass: Int? = null,
    val waistMeasurement: Int? = null,
    val fbg: Int? = null,
    val hdlCholesterol: Int? = null,
    val tg: Int? = null,
    val lowBloodPressure: Int? = null,
    val highBloodPressure: Int? = null
)

data class SendHealthStatus(
    val userNo: Int,
    val createTime: String,
    val height: Int? = null,
    val weight: Int? = null,
    val bmi: Int? = null,
    val bodyFatPercentage: Int? = null,
    val skeletalMuscleMass: Int? = null,
    val waistMeasurement: Int? = null,
    val fbg: Int? = null,
    val hdlCholesterol: Int? = null,
    val tg: Int? = null,
    val lowBloodPressure: Int? = null,
    val highBloodPressure: Int? = null
)

data class HealthAdvice(
    val userNo: Int,
    val createTime: String,
    val weight: String,
    val bmi: String,
    val fatPercentage: String,
    val waistMeasurement: String,
    val fbg: String,
    val hdlCholesterol: String,
    val tg: String,
    val bloodPressure: String,
    val skeletalMuscleMass: String
)
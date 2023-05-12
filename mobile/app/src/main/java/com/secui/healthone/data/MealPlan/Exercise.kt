package com.secui.healthone.data.MealPlan

data class ExerciseSearch(
    val no: Int,
    val name: String,
    val consumeKcal: Int,
    var isChecked: Boolean = false,
    var inputTimes: Int
)

data class ExerciseList(
    val no: Int,
    val userNo: Int,
    val name: String,
    val createTime: String,
    val workTime: Int,
    val consumeCalorie: Int,
    val heartRate: Int,
    val bloodPressure:Int
)

data class AddExercise(
    val userNo: Int,
    val name: String,
    val createTime: String,
    val workTime: Int,
    val consumeCalorie: Int,
    val heartRate: Int,
    val bloodPressure: Int,
)
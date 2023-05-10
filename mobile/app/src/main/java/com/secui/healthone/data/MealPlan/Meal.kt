package com.secui.healthone.data.MealPlan

data class Meal(
    val userNo: Int,
    val name: String,
    val createTime: String, // LocalDateTime 대신 String 사용
    val mealType: String,
    val gram: Int,
    val kcal: Int
)


enum class MealType {
    BREAKFAST, LUNCH, DINNER, SNACK
}

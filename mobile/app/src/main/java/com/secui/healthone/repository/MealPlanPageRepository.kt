package com.secui.healthone.repository

import com.secui.healthone.api.MealApi
import java.util.Calendar

data class CaloriesData(
    val intakeCalories: Int,
    val burnedCalories: Int,
    val totalCalories: Int,
    val recommendedCalories: Int
)

data class CaloriesApiResponse(
    val timestamp: String,
    val message: String,
    val data: CaloriesDataResponse,
    val success: Boolean
)

data class CaloriesDataResponse(
    val userNo: Int,
    val createTime: String,
    val sumKcalConsume: Int,
    val sumKcalEaten: Int
)
data class CaloriesWeekApiResponse(
    val timestamp: String,
    val message: String,
    val data: List<CaloriesDataResponse>,
    val success: Boolean
)
suspend fun fetchCaloriesData(date: String): CaloriesData? {
    val dateString = "${date}T00:00:00"
    return try {
        val apiResponse = fetchCalories(dateString)
        val intakeCalories = apiResponse.data.sumKcalEaten
        val burnedCalories = apiResponse.data.sumKcalConsume
        val totalCalories = intakeCalories - burnedCalories
        val recommendedCalories = 2500 // 적절한 권장 칼로리 계산 로직을 추가합니다.

        CaloriesData(
            intakeCalories = intakeCalories,
            burnedCalories = burnedCalories,
            totalCalories = totalCalories,
            recommendedCalories = recommendedCalories
        )
    } catch (e: Exception) {
        CaloriesData(
            intakeCalories = 0,
            burnedCalories = 0,
            totalCalories = 0,
            recommendedCalories = 0
        )
    }
}



suspend fun fetchCalories(date: String): CaloriesApiResponse {
    val caloriesApi = MealApi.create()
    val response = caloriesApi.getCalories(date)

    if (response.isSuccessful) {
        return response.body() ?: throw Exception("Failed to fetch calories data")
    } else {
        throw Exception("Failed to fetch calories data: ${response.errorBody()?.string()}")
    }
}
package com.secui.healthone.data.MealPlan

data class ExerciseSearchResponse(
    val timestamp: String,
    val message: String,
    val data: List<ExerciseSearch>,
    val success: Boolean
)

data class ExerciseListResponse<T>(
    val timestamp: String,
    val message: String,
    val data: T,
    val success: Boolean
)
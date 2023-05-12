package com.secui.healthone.data.MealPlan

data class FoodResponse(
    val timestamp: String,
    val message: String,
    val data: FoodDataResponse,
    val success: Boolean
)

data class FoodDataResponse(
    val content: List<Food>,
    val totalResults: Int
)
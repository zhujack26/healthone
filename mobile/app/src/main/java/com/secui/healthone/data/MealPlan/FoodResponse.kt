package com.secui.healthone.data.MealPlan

data class FoodResponse(
    val timestamp: String,
    val message: String,
    val data: List<Food>,
    val success: Boolean
)


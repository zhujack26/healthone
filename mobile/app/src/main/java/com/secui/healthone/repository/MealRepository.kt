package com.secui.healthone.data.MealPlan

import com.secui.healthone.api.MealApi
import retrofit2.Response

class MealRepository(private val mealApi: MealApi) {
    suspend fun getMealList(date: String, userNo: Int): Response<MealResponse> {
        return mealApi.getMealList(date, userNo)
    }
}
class FoodRepository(private val foodApi: MealApi.FoodApi) {
    suspend fun searchFood(searchTerm: String): Response<FoodResponse> {
        return foodApi.searchFood(searchTerm)
    }
}

package com.secui.healthone.data.MealPlan

import com.secui.healthone.api.MealApi
import retrofit2.Response

class MealRepository(private val mealApi: MealApi) {
    suspend fun getMealList(date: String, userNo: Int): Response<MealResponse<List<MealData>>> {
        return mealApi.getMealList(date, userNo)
    }
}

class FoodRepository(private val foodApi: MealApi) {
    suspend fun searchFood(searchTerm: String, page: Int = 1, size: Int = 20): Response<FoodResponse> {
        return foodApi.searchFood(searchTerm, page, size)
    }
}


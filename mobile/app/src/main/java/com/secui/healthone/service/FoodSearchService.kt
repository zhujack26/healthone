package com.secui.healthone.service

import com.secui.healthone.data.MealPlan.Food
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodSearchService {
    @GET("api/food/search")
    suspend fun searchFood(@Query("name") name: String): Response<List<Food>>
}

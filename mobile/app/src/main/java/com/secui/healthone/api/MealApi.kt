package com.secui.healthone.api

import com.secui.healthone.data.MealPlan.FoodResponse
import com.secui.healthone.data.MealPlan.Meal
import com.secui.healthone.data.MealPlan.MealData
import com.secui.healthone.data.MealPlan.MealResponse
import com.secui.healthone.repository.CaloriesApiResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MealApi {
    @POST("/api/meal")
    suspend fun addMeal(@Body meal: Meal): Response<Void>
    @DELETE("api/meal")
    suspend fun deleteMeal(@Query("no") mealNo: Int): Response<Unit>
    @GET("api/calorie")
    suspend fun getCalories(
        @Query("date") date: String
    ): Response<CaloriesApiResponse>
    @GET("api/meal/list")
    suspend fun getMealList(
        @Query("date") date: String,
        @Query("userno") userNo: Int
    ): Response<MealResponse<List<MealData>>>


    companion object {
        private const val BASE_URL = "http://a80d3a967a5514702bfe8ba3e8b52871-1335940738.ap-northeast-2.elb.amazonaws.com/"

        fun create(): MealApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MealApi::class.java)
        }
    }
}

interface FoodApi {
    @GET("api/food/search")
    suspend fun searchFood(
        @Query("name") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): retrofit2.Response<FoodResponse>

    companion object {
        private const val BASE_URL = "http://a80d3a967a5514702bfe8ba3e8b52871-1335940738.ap-northeast-2.elb.amazonaws.com/"

        fun create(): FoodApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FoodApi::class.java)
        }
    }
}

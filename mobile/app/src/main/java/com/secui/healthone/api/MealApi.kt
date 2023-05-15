package com.secui.healthone.api

import com.secui.healthone.data.MealPlan.FoodResponse
import com.secui.healthone.data.MealPlan.Meal
import com.secui.healthone.data.MealPlan.MealData
import com.secui.healthone.data.MealPlan.MealResponse
import com.secui.healthone.instance.HeartRateInstance
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
    @POST("/api/meal-record")
    suspend fun addMeal(@Body meal: Meal): Response<Void>
    @DELETE("api/meal-record")
    suspend fun deleteMeal(@Query("no") mealNo: Int): Response<Unit>
    @GET("api/calorie")
    suspend fun getCalories(
        @Query("date") date: String
    ): Response<CaloriesApiResponse>
    @GET("api/meal-record/list")
    suspend fun getMealList(
        @Query("date") date: String,
        @Query("userno") userNo: Int
    ): Response<MealResponse<List<MealData>>>

    @GET("api/food/search")
    suspend fun searchFood(
        @Query("name") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): retrofit2.Response<FoodResponse>


    companion object {
        fun create(): MealApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val authInterceptor = Interceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${HeartRateInstance.tempAccToken}")
                    .build()

                chain.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(authInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://meal.apihealthone.com/")
                .client(client)
                .build()

            return retrofit.create(MealApi::class.java)
        }
    }
}

package com.secui.healthone.api

import com.secui.healthone.data.MealPlan.FoodResponse
import com.secui.healthone.data.MealPlan.MealResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("api/meal/list")
    suspend fun getMealList(
        @Query("date") date: String,
        @Query("userno") userNo: Int
    ): Response<MealResponse>

    interface FoodApi {
        @GET("api/food/search")
        suspend fun searchFood(@Query("name") name: String): Response<FoodResponse>

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
    companion object {
        private const val BASE_URL = "http://a80d3a967a5514702bfe8ba3e8b52871-1335940738.ap-northeast-2.elb.amazonaws.com/"

        fun create(): MealApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MealApi::class.java)
        }
    }
}
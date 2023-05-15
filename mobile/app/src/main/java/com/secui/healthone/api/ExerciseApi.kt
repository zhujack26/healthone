package com.secui.healthone.api

import com.secui.healthone.data.MealPlan.AddExercise
import com.secui.healthone.data.MealPlan.ExerciseList
import com.secui.healthone.data.MealPlan.ExerciseListResponse
import com.secui.healthone.data.MealPlan.ExerciseSearchResponse
import com.secui.healthone.instance.HeartRateInstance
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ExerciseApi {
    @GET("api/sportrecord")
    suspend fun getExerciseList(
        @Query("date") date: String,
    ): Response<ExerciseListResponse<List<ExerciseList>>>

    @GET("api/sport/search")
    suspend fun searchExercise(
        @Query("name") query: String,
    ):retrofit2.Response<ExerciseSearchResponse>

    @DELETE("api/sportrecord")
    suspend fun deleteExercise(@Query("no") exerciseNo: Int): Response<Unit>

    @POST("/api/sportrecord")
    suspend fun addExercise(@Body exercise: AddExercise): Response<Void>
    companion object {
        private const val BASE_URL = "http://meal.apihealthone.com/"

        fun create(): ExerciseApi {
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

                return retrofit.create(ExerciseApi::class.java)
        }
    }
}

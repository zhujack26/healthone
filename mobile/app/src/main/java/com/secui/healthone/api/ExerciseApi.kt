package com.secui.healthone.api

import com.secui.healthone.data.MealPlan.AddExercise
import com.secui.healthone.data.MealPlan.ExerciseList
import com.secui.healthone.data.MealPlan.ExerciseListResponse
import com.secui.healthone.data.MealPlan.ExerciseSearchResponse

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

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExerciseApi::class.java)
        }
    }
}

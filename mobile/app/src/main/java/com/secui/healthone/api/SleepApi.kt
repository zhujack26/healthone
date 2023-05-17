package com.secui.healthone.api

import com.secui.healthone.data.ApiResponse
import com.secui.healthone.data.Sleep.SleepRecord
import com.secui.healthone.instance.HeartRateInstance
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query

interface SleepApi {
    @POST("api/sleep")
    suspend fun postSleepRecord(@Body sleepRecord: SleepRecord): ApiResponse<SleepRecord>
    @GET("api/sleep/detail")
    suspend fun getSleepRecords(
        @Query("date") query: String
    ): ApiResponse<List<SleepRecord>>
    @DELETE("api/sleep")
    suspend fun deleteSleepRecord(@Query("no") sleepRecordId: Int): ApiResponse<SleepRecord>

    companion object {
        fun create(): SleepApi {
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
                .baseUrl("https://back.apihealthone.com/check/")
                .client(client)
                .build()

            return retrofit.create(SleepApi::class.java)
        }
    }
}

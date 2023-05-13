package com.secui.healthone.api

import com.secui.healthone.data.ApiResponse
import com.secui.healthone.data.HealthStatus.HealthStatus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface HealthStatusApi {
    @GET("api/health-stat")
    suspend fun getHealthStatus(
        @Query("date") query: String
    )
    : ApiResponse<List<HealthStatus>>


    companion object {
        fun create(): HealthStatusApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://info.apihealthone.com/")
                .client(client)
                .build()

            return retrofit.create(HealthStatusApi::class.java)
        }
    }
}

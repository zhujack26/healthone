package com.secui.healthone.api

import com.secui.healthone.data.ApiResponse
import com.secui.healthone.data.HealthStatus.HealthAdvice
import com.secui.healthone.data.HealthStatus.HealthStatus
import com.secui.healthone.data.HealthStatus.SendHealthStatus
import com.secui.healthone.instance.HeartRateInstance
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

val JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJubyI6MX0.gVYK5lIyuAjPAu9IgGSnIQEa3W9h-qKepH54k--_gw4"

interface HealthStatusApi {
    @GET("api/health-stat")
    suspend fun getHealthStatus(@Query("date") query: String): ApiResponse<List<HealthStatus>>

    @POST("api/health-stat")
    suspend fun postHealthStatus(@Body healthStatus: SendHealthStatus): Response<Void>

    @GET("api/health-advice")
    suspend fun getHealthAdvice():ApiResponse<HealthAdvice>

    companion object {
        fun create(): HealthStatusApi {
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
                .baseUrl("http://info.apihealthone.com/")
                .client(client)
                .build()

            return retrofit.create(HealthStatusApi::class.java)
        }
    }
}

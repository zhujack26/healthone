package com.secui.healthone.instance

import com.secui.healthone.api.fit.HealthInfoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HealthInfoInstance {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://back.apihealthone.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api: HealthInfoApi = retrofit.create(HealthInfoApi::class.java)
}
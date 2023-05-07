package com.secui.healthone.util

import com.secui.healthone.service.WalkApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://check.apihealthone.com/"

    val instance: WalkApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(WalkApiService::class.java)
    }
}
package com.secui.healthone.instance

import com.secui.healthone.api.fit.HealthInfoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HealthInfoInstance {
    private const val BASE_URL = "http://info.apihealthone.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val healthInfoApi: HealthInfoApi = retrofit.create(HealthInfoApi::class.java)
}
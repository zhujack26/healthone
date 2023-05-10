package com.secui.healthone.api

import com.secui.healthone.data.ApiResponse
import com.secui.healthone.data.Sleep.SleepRecord
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface SleepApi {
    @POST("/api/sleep")
    suspend fun postSleepRecord(@Body sleepRecord: SleepRecord): ApiResponse<SleepRecord>

    companion object {
        fun create(): SleepApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://check.apihealthone.com/")
                .build()

            return retrofit.create(SleepApi::class.java)
        }
    }
}

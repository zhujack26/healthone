package com.secui.healthone.service

import com.secui.healthone.data.heart.HeartWrite
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HeartRateService {

    @GET("/api/heart-rate")
    suspend fun getHeartRateList(@Query("page") page:Int, @Query("size") size:Int): Response<ResponseBody>

    @POST("/api/heart-rate")
    suspend fun writeHeartRate(@Body heartWrite:HeartWrite):Response<ResponseBody>


}


package com.secui.healthone.service

import com.secui.healthone.data.ApiResponse
import com.secui.healthone.data.WalkData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WalkApiService {
    @GET("api/walk")
    suspend fun getWalkData(@Query("dateTime") dateTime: String): Response<ApiResponse<List<WalkData>>>

    @POST("api/walk")
    suspend fun postWalkData(@Body walkData: WalkData): Response<ApiResponse<List<WalkData>>>
}
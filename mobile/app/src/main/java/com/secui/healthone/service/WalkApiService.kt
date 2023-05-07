package com.secui.healthone.service

import com.secui.healthone.data.WalkData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WalkApiService {
    @POST("api/walk")
    suspend fun postWalkData(@Body walkData: WalkData): Response<List<WalkData>>
}
package com.secui.healthone.api.fit

import com.secui.healthone.data.HealthInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH

interface HealthInfoApi {
    @PATCH("info/api/health-info")
    suspend fun updateHealthInfo(
        @Header("Authorization") accessToken: String,
        @Body healthInfo: HealthInfo
    ): Response<Unit>
}
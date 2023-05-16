package com.secui.healthone.api.fit

import com.secui.healthone.data.HealthInfo
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

interface HealthInfoApi {
    @PATCH("api/health-info")
    suspend fun updateHealthInfo(
        @Header("Authorization") token: String,
        @Body healthInfo: HealthInfo
    ): HealthInfo
}
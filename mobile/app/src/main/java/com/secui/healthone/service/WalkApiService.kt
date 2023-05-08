package com.secui.healthone.service

<<<<<<< HEAD
=======
import com.secui.healthone.data.ApiResponse
>>>>>>> 48ed6455598a786f90b976d86faa3b9addc1deef
import com.secui.healthone.data.WalkData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WalkApiService {
    @GET("api/walk")
<<<<<<< HEAD
    suspend fun getWalkData(@Query("dateTime") dateTime: String): Response<List<Int>>

    @POST("api/walk")
    suspend fun postWalkData(@Body walkData: WalkData): Response<List<WalkData>>
=======
    suspend fun getWalkData(@Query("dateTime") dateTime: String): Response<ApiResponse<List<WalkData>>>

    @POST("api/walk")
    suspend fun postWalkData(@Body walkData: WalkData): Response<ApiResponse<List<WalkData>>>
>>>>>>> 48ed6455598a786f90b976d86faa3b9addc1deef
}
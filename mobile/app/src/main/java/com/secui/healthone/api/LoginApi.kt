package com.secui.healthone.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface LoginApi {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun sendAuthCodeToServer(@Field("authCode") authCode: String): Response<ResponseBody>

    @POST("auth/verify")
    suspend fun verifyAuth(@Header("Authorization") authHeader: String): Response<ResponseBody>
}
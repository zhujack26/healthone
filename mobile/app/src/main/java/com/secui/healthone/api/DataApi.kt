package com.secui.healthone.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header

interface DataApi {
    @GET("info/check-data-download/sleep")
    suspend fun downloadSleepData(@Header("Authorization") accessToken: String): ResponseBody

    @GET("info/check-data-download/healthinfo")
    suspend fun downloadHealthData(@Header("Authorization") accessToken: String): ResponseBody

    @GET("check/check-data-download/walk")
    suspend fun downloadWalkData(@Header("Authorization") accessToken: String): ResponseBody

    @GET("check/check-data-download/sleep")
    suspend fun downloadSleepData(@Header("Authorization") accessToken: String): ResponseBody

    @GET("check/check-data-download/heart-rate")
    suspend fun downloadHeartRateData(@Header("Authorization") accessToken: String): ResponseBody
}
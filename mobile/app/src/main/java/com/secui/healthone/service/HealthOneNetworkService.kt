package com.secui.healthone.service

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.Objects

interface HealthOneNetworkService {

    @POST("/test")
    suspend fun sendRequest(@Body authCode:String) : Object;

    @GET("/test")
    suspend fun sendGetRequest():Object;

    @GET("/test")
    suspend fun getFoodInfo():Object;

}


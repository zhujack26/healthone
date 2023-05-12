package com.secui.healthone.service

import com.secui.healthone.instance.ChallengeInstance
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ChallengeService {

    @GET("/api/challenge")
    suspend fun getChallengeList():Response<ResponseBody>

}
package com.secui.healthone.repository

import com.secui.healthone.instance.ChallengeInstance
import okhttp3.ResponseBody
import retrofit2.Response

class ChallengeRepository {

    suspend fun getChallengeList():Response<ResponseBody>{
        return ChallengeInstance.RetrofitInstance.api.getChallengeList()
    }
}
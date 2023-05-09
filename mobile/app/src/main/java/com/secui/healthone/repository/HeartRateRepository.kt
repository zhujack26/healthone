package com.secui.healthone.repository

import com.secui.healthone.data.heart.HeartWrite
import com.secui.healthone.instance.HeartRateInstance
import okhttp3.ResponseBody
import retrofit2.Response

class HeartRateRepository {
    // /api/heart-rate
    suspend fun getHeartRateList(page:Int, size:Int): Response<ResponseBody>{
        return HeartRateInstance.RetrofitInstance.api.getHeartRateList(page, size);
    }

    suspend fun writeHeartRate(heartWrite: HeartWrite):Response<ResponseBody>{
        return HeartRateInstance.RetrofitInstance.api.writeHeartRate(heartWrite);
    }
}


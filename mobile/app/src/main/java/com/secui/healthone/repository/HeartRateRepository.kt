package com.secui.healthone.repository

import com.secui.healthone.data.heart.HeartWrite
<<<<<<< HEAD
import com.secui.healthone.util.HeartRateInstance
=======
import com.secui.healthone.instance.HeartRateInstance
>>>>>>> 3edb1a49e4e48cd47aa875fd65138e4308d8ce9a
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


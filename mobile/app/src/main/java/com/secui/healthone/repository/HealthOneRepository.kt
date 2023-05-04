package com.secui.healthone.repository

import com.secui.healthone.util.HealthOneRetrofitInstance
import retrofit2.http.Body
import java.util.Objects

class HealthOneRepository {
    suspend fun sendRequest(authCode:String) : Object {
        return HealthOneRetrofitInstance.RetrofitInstance.api.sendRequest(authCode);
    }

    suspend fun sendGetRequest() : Object {
        return HealthOneRetrofitInstance.RetrofitInstance.api.sendGetRequest();
    }

    suspend fun getFoodInfo(): Object {
        return HealthOneRetrofitInstance.RetrofitInstance.api.getFoodInfo();
    }
}


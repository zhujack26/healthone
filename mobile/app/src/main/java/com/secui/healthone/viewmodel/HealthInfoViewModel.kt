package com.secui.healthone.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.api.fit.HealthInfoApi
import com.secui.healthone.data.HealthInfo
import kotlinx.coroutines.launch

class HealthInfoViewModel(private val healthInfoApi: HealthInfoApi) : ViewModel() {
    fun updateHealthInfo(accessToken: String, healthInfo: HealthInfo) {
        viewModelScope.launch {
            try {
                val response = healthInfoApi.updateHealthInfo("Bearer $accessToken", healthInfo)
                if (response.isSuccessful) {
                    Log.d("HealthInfoViewModel", "PATCH request was successful. Response: ${response.body()}")
                } else {
                    Log.e("HealthInfoViewModel", "An error occurred: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("HealthInfoViewModel", "An error occurred", e)
            }
        }
    }
}
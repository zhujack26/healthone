package com.secui.healthone.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.api.HealthStatusApi
import com.secui.healthone.data.HealthStatus.HealthAdvice
import com.secui.healthone.data.HealthStatus.HealthStatus
import com.secui.healthone.data.HealthStatus.SendHealthStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HealthStatusViewModel : ViewModel() {
    private val HealthStatusApiService = HealthStatusApi.create()
    val healthRecords = mutableStateListOf<HealthStatus>()
    val healthAdvice = mutableStateOf<HealthAdvice?>(null)
    fun fetchHealthRecords(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = HealthStatusApiService.getHealthStatus(date)
            if (response.success) {
                healthRecords.clear()
                healthRecords.addAll(response.data)
            } else {
                // Handle the error...
            }
        }
    }

    suspend fun addHealthStatus(HealthStatus: SendHealthStatus) {
        Log.d("HealthStatusViewModel", "addHealth called with exercise: $HealthStatus")
        try {
            val response = HealthStatusApiService.postHealthStatus(HealthStatus)
            Log.d("HealthStatusViewModel", "${response}")
            if (response.isSuccessful) {
                Log.d("HealthViewModel", "Request URL: ${response}")
            } else {
                Log.e(
                    "HealthViewModel",
                    "Request failed with code: ${response.code()} and message: ${response.message()}"
                )
            }
        } catch (e: Exception) {
            Log.e("HealthViewModel", "Error occurred while sending request: ", e)
        }
    }

    fun fetchHealthAdvice() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = HealthStatusApiService.getHealthAdvice()
            if (response.success) {
                healthAdvice.value = response.data
            } else {
                // Handle the error...
            }
        }
    }
}

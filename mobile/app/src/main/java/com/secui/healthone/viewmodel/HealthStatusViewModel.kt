package com.secui.healthone.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.api.HealthStatusApi
import com.secui.healthone.data.HealthStatus.HealthStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HealthStatusViewModel : ViewModel() {
    private val HealthStatusApiService = HealthStatusApi.create()
    val healthRecords = mutableStateListOf<HealthStatus>()

    fun fetchHealthRecords(date: String){
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
    }
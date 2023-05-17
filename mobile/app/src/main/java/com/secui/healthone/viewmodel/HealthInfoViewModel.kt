package com.secui.healthone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.data.HealthInfo
import com.secui.healthone.instance.HealthInfoInstance
import kotlinx.coroutines.launch

class HealthInfoViewModel : ViewModel() {
    private val api = HealthInfoInstance.healthInfoApi

    fun updateHealthInfo(token: String, healthInfo: HealthInfo) {
        viewModelScope.launch {
            try {
                val updatedHealthInfo = api.updateHealthInfo("Bearer $token", healthInfo)
            } catch (e: Exception) {
            }
        }
    }
}
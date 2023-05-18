package com.secui.healthone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.secui.healthone.api.fit.HealthInfoApi

class HealthInfoViewModelFactory(private val healthInfoApi: HealthInfoApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HealthInfoViewModel::class.java)) {
            return HealthInfoViewModel(healthInfoApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
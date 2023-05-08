package com.secui.healthone.compose.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.secui.healthone.repository.HealthOneRepository
import com.secui.healthone.viewmodel.HealthOneViewModel

class MainViewModelFactory(
    private val repository : HealthOneRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HealthOneViewModel(repository) as T
    }
}
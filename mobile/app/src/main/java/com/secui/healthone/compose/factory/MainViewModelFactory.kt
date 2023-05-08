package com.secui.healthone.compose.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.secui.healthone.repository.HeartRateRepository
import com.secui.healthone.viewmodel.HeartRateViewModel

class MainViewModelFactory(
    private val repository : HeartRateRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HeartRateViewModel(repository) as T
    }
}
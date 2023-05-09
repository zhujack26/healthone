package com.secui.healthone.compose.factory

import androidx.lifecycle.ViewModelProvider
<<<<<<< HEAD
import com.secui.healthone.repository.HeartRateRepository
import com.secui.healthone.viewmodel.HeartRateViewModel

class MainViewModelFactory(
    private val repository : HeartRateRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HeartRateViewModel(repository) as T
    }
=======

class MainViewModelFactory(
    // private val repository : HealthOneRepository
) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return HealthOneViewModel(repository) as T
//    }
>>>>>>> 3edb1a49e4e48cd47aa875fd65138e4308d8ce9a
}
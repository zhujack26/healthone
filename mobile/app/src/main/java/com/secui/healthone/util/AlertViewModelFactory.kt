package com.secui.healthone.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.secui.healthone.viewmodel.AlertViewModel

class AlertViewModelFactory(private val context: Context, private val prefs: PreferencesManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlertViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlertViewModel(context, prefs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
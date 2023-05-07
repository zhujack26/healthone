package com.secui.healthone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.util.RetrofitClient
import com.secui.healthone.data.WalkData
import kotlinx.coroutines.launch

class WalkViewModel : ViewModel() {
    fun postWalkData(walkData: WalkData) {
        viewModelScope.launch {
            RetrofitClient.instance.postWalkData(walkData)
        }
    }
}
package com.secui.healthone.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.data.WalkData
import com.secui.healthone.util.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class WalkViewModel : ViewModel() {
    suspend fun postWalkData(walkData: WalkData): Response<List<WalkData>> {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.postWalkData(walkData)
        }
    }
}
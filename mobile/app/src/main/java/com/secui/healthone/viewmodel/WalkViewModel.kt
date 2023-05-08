package com.secui.healthone.viewmodel

import WalkRepository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.data.WalkData
import com.secui.healthone.util.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class WalkViewModel : ViewModel() {

    private val walkRepository = WalkRepository()

    fun getPastWeekWalkData(): MutableLiveData<List<Int>> {
        val walkData = MutableLiveData<List<Int>>()
        viewModelScope.launch {
            walkData.value = walkRepository.getPastWeekWalkData()
        }
        return walkData
    }

    suspend fun postWalkData(walkData: WalkData): Response<List<WalkData>> {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.postWalkData(walkData)
        }
    }
}

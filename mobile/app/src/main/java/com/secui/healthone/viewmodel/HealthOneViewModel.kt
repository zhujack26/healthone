package com.secui.healthone.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.repository.HealthOneRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.Objects

class HealthOneViewModel(private val repository : HealthOneRepository) : ViewModel() {
    val myResponse: MutableLiveData<Object> = MutableLiveData() // post >> json place holder


    val foodResponse: MutableLiveData<Object> = MutableLiveData();

    fun requestData(authCode:String) {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.sendRequest(authCode);
            myResponse.value = response
        }
    }

    fun sendGetRequest(){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.sendGetRequest();
            myResponse.value = response
            Log.d("HealthOneViewModel", "${response.toString()}")
        }
    }

    // 식사정보
    fun getFoodInfo(no:Int){
        Log.d("LOGGG", "요청 드가자.....")
        viewModelScope.launch {
            val response = repository.getFoodInfo();
            Log.d("LOGGG", "요청보냄...")

            foodResponse.value = response;
        }
    }

    // 코루틴 에러 핸들러 >> coroutine exception Handelr
    protected val exceptionHandler = CoroutineExceptionHandler{ i, exception ->
        Log.d("ERR ::::", "에러 발생.... ${exception.message}");
        Log.d("ERR ::::", "에러 발생.... ${exception.toString()}");
    }

}
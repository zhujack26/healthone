package com.secui.healthone.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.api.SleepApi
import com.secui.healthone.data.Sleep.SleepRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SleepViewModel : ViewModel() {
    private val sleepApiService = SleepApi.create()
    val sleepRecords = mutableStateListOf<SleepRecord>()
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun saveSleepRecord(selectedSleepTime: String, selectedWakeTime: String) {
        if (selectedSleepTime.isNotEmpty() && selectedWakeTime.isNotEmpty()) {
            try {
                val formattedSleepTime = convertToServerTimeFormat(selectedSleepTime)
                val formattedWakeTime = convertToServerTimeFormat(selectedWakeTime)

                for (record in sleepRecords) {
                    if ((formattedSleepTime >= record.startSleepTime && formattedSleepTime <= record.endSleepTime) ||
                        (formattedWakeTime >= record.startSleepTime && formattedWakeTime <= record.endSleepTime)) {
                        _errorMessage.postValue("이미 등록된 수면시간입니다.")
                        return
                    }
                }

                val sleepRecord = SleepRecord(
                    userNo = 1,
                    createTime = getCurrentTimestamp(),
                    startSleepTime = formattedSleepTime,
                    endSleepTime = formattedWakeTime
                )
                viewModelScope.launch(Dispatchers.IO) {
                    val response = sleepApiService.postSleepRecord(sleepRecord)
                    if (response.success) {
                        fetchSleepRecords(formattedSleepTime.substring(0, 10))
                        withContext(Dispatchers.Main) {
                        }
                    } else {
                        // Handle the error
                    }
                }
            } catch (e: ParseException) {
                _errorMessage.postValue("Invalid time format.")
            }
        }
    }

    fun fetchSleepRecords(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = sleepApiService.getSleepRecords(date)
            if (response.success) {
                sleepRecords.clear()
                sleepRecords.addAll(response.data)
            } else {
                // Handle the error...
            }
        }
    }

    fun deleteSleepRecord(index: Int) {
        val sleepRecord = sleepRecords[index]
        sleepRecords.removeAt(index)

        viewModelScope.launch(Dispatchers.IO) {
            val response = sleepApiService.deleteSleepRecord(sleepRecord.no!!)
            if (response.success) {
                // After deleting the record, fetch the records from the server
                fetchSleepRecords(sleepRecord.startSleepTime!!.substring(0, 10))
            } else {
                // Handle the error
            }
        }
    }
}

fun convertToServerTimeFormat(time: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    var date: Date? = null
    try {
        date = inputFormat.parse(time)
    } catch (e: ParseException) {
        Log.e("SleepViewModel", "Failed to parse time: $time", e)
    }
    return if (date != null) outputFormat.format(date) else ""
}

fun getCurrentTimestamp(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    return sdf.format(Date())
}

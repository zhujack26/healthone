package com.secui.healthone.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.api.SleepApi
import com.secui.healthone.data.Sleep.SleepRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SleepViewModel : ViewModel() {
    private val sleepApiService = SleepApi.create()
    val sleepRecords = mutableStateListOf<SleepRecord>()

    fun saveSleepRecord(selectedSleepTime: String, selectedWakeTime: String) {
        if (selectedSleepTime.isNotEmpty() && selectedWakeTime.isNotEmpty()) {
            try {
                val formattedSleepTime = convertToServerTimeFormat(selectedSleepTime)
                val formattedWakeTime = convertToServerTimeFormat(selectedWakeTime)
                val sleepRecord = SleepRecord(
                    userNo = 1,
                    createTime = getCurrentTimestamp(),
                    startSleepTime = formattedSleepTime,
                    endSleepTime = formattedWakeTime
                )

                viewModelScope.launch(Dispatchers.IO) {
                    val response = sleepApiService.postSleepRecord(sleepRecord)
                    if (response.success) {
                        // After saving the record, fetch the records from the server
                        fetchSleepRecords(formattedSleepTime.substring(0, 10))
                    } else {
                        // Handle the error
                    }
                }

            } catch (e: ParseException) {
                // Handle the error...
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

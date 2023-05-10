package com.secui.healthone.ui.sleep

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.secui.healthone.api.SleepApi
import com.secui.healthone.data.Sleep.SleepRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SleepRecords(selectedSleepTime: MutableState<String>, selectedWakeTime: MutableState<String>) {
    val sleepRecords = remember { mutableStateListOf<SleepRecord>() }
    val showInputFields = remember { mutableStateOf(sleepRecords.isEmpty()) }
    val sleepApiService = remember { SleepApi.create() }

    fun saveSleepRecord() {
        if (selectedSleepTime.value.isNotEmpty() && selectedWakeTime.value.isNotEmpty()) {
            val sleepRecord = SleepRecord(
                userNo = 1,
                createTime = "2023-05-10T12:44:45.394Z",
                startSleepTime = selectedSleepTime.value,
                endSleepTime = selectedWakeTime.value
            )
            sleepRecords.add(sleepRecord)
            selectedSleepTime.value = ""
            selectedWakeTime.value = ""
            showInputFields.value = false

            CoroutineScope(Dispatchers.IO).launch {
                val response = sleepApiService.postSleepRecord(sleepRecord)
                // handle response if needed
            }
        }
    }


    Column {
        if (showInputFields.value) {
            Button(onClick = ::saveSleepRecord) {
                Text("저장")
            }
        } else {
            Button(onClick = { showInputFields.value = true }) {
                Text("수면 기록 추가")
            }
        }

        sleepRecords.forEach { sleepRecord ->
            Text("취침 시간: ${sleepRecord.startSleepTime}, 기상 시간: ${sleepRecord.endSleepTime}")
        }
    }
}

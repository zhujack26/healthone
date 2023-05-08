package com.secui.healthone.ui.sleep

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
<<<<<<< HEAD
import com.secui.healthone.data.Sleep.SleepRecord
=======
import com.secui.healthone.repository.Sleep.SleepRecord
>>>>>>> 48ed6455598a786f90b976d86faa3b9addc1deef


@Composable
fun SleepRecords(selectedSleepTime: MutableState<String>, selectedWakeTime: MutableState<String>) {
    val sleepRecords = remember { mutableStateListOf<SleepRecord>() }
    val showInputFields = remember { mutableStateOf(sleepRecords.isEmpty()) }

    fun saveSleepRecord() {
        if (selectedSleepTime.value.isNotEmpty() && selectedWakeTime.value.isNotEmpty()) {
            sleepRecords.add(SleepRecord(selectedSleepTime.value, selectedWakeTime.value))
            selectedSleepTime.value = ""
            selectedWakeTime.value = ""
            showInputFields.value = false
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
            Text("취침 시간: ${sleepRecord.sleepTime}, 기상 시간: ${sleepRecord.wakeTime}")
        }
    }
}

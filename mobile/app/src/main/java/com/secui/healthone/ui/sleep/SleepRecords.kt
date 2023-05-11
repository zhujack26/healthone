package com.secui.healthone.ui.sleep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.secui.healthone.viewmodel.SleepViewModel

@Composable
fun SleepRecords(viewModel: SleepViewModel, selectedSleepTime: MutableState<String>, selectedWakeTime: MutableState<String>) {
    val showInputFields = remember { mutableStateOf(viewModel.sleepRecords.isEmpty()) }

    Column {
        if (showInputFields.value) {
            Button(
                onClick = {
                    viewModel.saveSleepRecord(selectedSleepTime.value, selectedWakeTime.value)
                },
                modifier = Modifier.width(200.dp).align(Alignment.CenterHorizontally)
            ) {
                Text("저장")
            }
        } else {
            Button(
                onClick = { showInputFields.value = true },
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            ) {
                Text("수면 기록 추가")
            }
        }

        viewModel.sleepRecords.forEachIndexed { index, sleepRecord ->
            SleepRecordCard(sleepRecord = sleepRecord, index = index, viewModel = viewModel)
        }
    }
}

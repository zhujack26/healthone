package com.secui.healthone.ui.sleep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.repository.Sleep.SleepRecord

@Composable
fun CustomSleepGoal(
    selectedSleepTime: MutableState<String>,
    selectedWakeTime: MutableState<String>,
    sleepRecords: MutableList<SleepRecord>
) {
    val sleepTime = remember { mutableStateOf("") }
    val wakeTime = remember { mutableStateOf("") }
    val sleepDuration = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimePickerRow("취침 시간", sleepTime, sleepRecords)
        TimePickerRow("기상 시간", wakeTime, sleepRecords)

        val sleepAngle = sleepTime.value.takeIf { it.isNotEmpty() }?.let {
            calculateAngleFromTime(it)
        } ?: 0f

        val wakeAngle = wakeTime.value.takeIf { it.isNotEmpty() }?.let {
            calculateAngleFromTime(it)
        } ?: 0f
        LaunchedEffect(sleepTime.value, wakeTime.value) {
            if (sleepTime.value.isNotEmpty() && wakeTime.value.isNotEmpty()) {
                selectedSleepTime.value = sleepTime.value
                selectedWakeTime.value = wakeTime.value
                calculateSleepDuration(sleepTime, wakeTime, sleepDuration)
            }
        }

        Text(
            text = "수면 기간 : ${sleepDuration.value}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        SleepTimeClock(sleepTime = sleepTime.value, wakeTime = wakeTime.value)
    }
}

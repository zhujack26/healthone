package com.secui.healthone.ui.datacollectpage


import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.*
import android.widget.TimePicker
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun SleepGoal() {
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val sleepTime = remember { mutableStateOf("") }
    val wakeTime = remember { mutableStateOf("") }
    val sleepDuration = remember { mutableStateOf("") }

        AndroidView(
            factory = { context ->
                val timePicker = TimePicker(context).apply {
                    setIs24HourView(false)
                    setOnTimeChangedListener { _, hourOfDay, minute ->
                        val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                        if (sleepTime.value.isBlank()) {
                            sleepTime.value = selectedTime
                        } else if (wakeTime.value.isBlank()) {
                            wakeTime.value = selectedTime
                        }
                        calculateSleepDuration(sleepTime, wakeTime, sleepDuration)
                    }
                }
                timePicker
            },
            update = { view ->
                view.hour = hour
                view.minute = minute
            }
        )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "취침 시간 : ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)
            Text(
                text = sleepTime.value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { sleepTime.value = "" }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "기상 시간 : ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = wakeTime.value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { wakeTime.value = "" }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "${sleepDuration.value}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun calculateSleepDuration(
    sleepTime: MutableState<String>,
    wakeTime: MutableState<String>,
    sleepDuration: MutableState<String>
) {
    if (sleepTime.value.isNotBlank() && wakeTime.value.isNotBlank()) {
        val sleepHour = sleepTime.value.substringBefore(":").toInt()
        val sleepMinute = sleepTime.value.substringAfter(":").toInt()
        val wakeHour = wakeTime.value.substringBefore(":").toInt()
        val wakeMinute = wakeTime.value.substringAfter(":").toInt()

        val sleepCalendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, sleepHour)
            set(Calendar.MINUTE, sleepMinute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val wakeCalendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, wakeHour)
            set(Calendar.MINUTE, wakeMinute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val duration = wakeCalendar.timeInMillis - sleepCalendar.timeInMillis
        val hour = duration / (1000 * 60 * 60)
        val minute = (duration % (1000 * 60 * 60)) / (1000 * 60)

        sleepDuration.value = "$hour 시간 $minute 분"
    }
}
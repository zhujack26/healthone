package com.secui.healthone.ui.datacollectpage


import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*
import android.widget.TimePicker
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "취침 시간 : ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
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
            Spacer(modifier = Modifier.size(16.dp))
        }
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
        var duration = wakeCalendar.timeInMillis - sleepCalendar.timeInMillis
        if (duration <= 0) { // 기상 시간이 취침 시간보다 이전인 경우 (다음날로 넘어갔을 경우)
            duration += 24 * 60 * 60 * 1000
        }

        val totalHours = duration / (1000 * 60 * 60)
        val totalMinutes = (duration % (1000 * 60 * 60)) / (1000 * 60)

        // 24시간 이상인 경우, 24시간으로 제한하지 않고, 24시간 단위로 계산함
        if (totalHours >= 24) {
            val days = totalHours / 24
            val hours = totalHours % 24
            sleepDuration.value = "${days}일 ${hours}시간 ${totalMinutes}분"
        } else {
            sleepDuration.value = "${totalHours}시간 ${totalMinutes}분"
        }
    }
}
package com.secui.healthone.ui.datacollectpage


import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.util.PreferencesManager
import java.text.SimpleDateFormat

@Composable
fun SleepGoal(context: Context) {
    val context = LocalContext.current
    val preferencesManager = PreferencesManager(context)

    val sleepTime = remember { mutableStateOf(preferencesManager.getSleepTime()) }
    val wakeTime = remember { mutableStateOf(preferencesManager.getWakeTime()) }
    val sleepDuration = remember { mutableStateOf(preferencesManager.getSleepDuration()) }

    val sleepEditMode = remember { mutableStateOf(false) }
    val wakeEditMode = remember { mutableStateOf(false) }

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                factory = { context ->
                    val timePicker = TimePicker(context).apply {
                        setIs24HourView(true)
                        setOnTimeChangedListener { _, hourOfDay, minute ->
                            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                            val calendar = Calendar.getInstance().apply {
                                set(Calendar.HOUR_OF_DAY, hourOfDay)
                                set(Calendar.MINUTE, minute)
                            }
                            val selectedTime = sdf.format(calendar.time)
                            if (sleepEditMode.value) {
                                sleepTime.value = selectedTime
                            } else if (wakeEditMode.value) {
                                wakeTime.value = selectedTime
                            }
                            if (sleepEditMode.value || wakeEditMode.value) {
                                calculateSleepDuration(sleepTime, wakeTime, sleepDuration)
                            }
                        }
                        setBackgroundColor(AppColors.green200.toArgb())
                    }
                    timePicker
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
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
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = if (sleepEditMode.value) "완료" else "수정",
                    modifier = Modifier.clickable {
                        sleepEditMode.value = !sleepEditMode.value
                        if (!sleepEditMode.value) { // 완료를 누른 경우
                            preferencesManager.setSleepTime(sleepTime.value)
                        }
                    },
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
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
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = if (wakeEditMode.value) "완료" else "수정",
                    modifier = Modifier.clickable {
                        wakeEditMode.value = !wakeEditMode.value
                        if (!wakeEditMode.value) { // 완료를 누른 경우
                            preferencesManager.setWakeTime(wakeTime.value)
                        }
                    },
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
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
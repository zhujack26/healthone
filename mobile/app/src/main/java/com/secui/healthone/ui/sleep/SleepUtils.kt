package com.secui.healthone.ui.sleep

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

fun calculateAngleFromTime(time: String): Float {
    val (hour, minute) = time.split(':').map(String::toInt)
    val totalMinutes = hour * 60 + minute
    return totalMinutes / (24f * 60f) * 360f - 90f
}

fun calculateSleepDuration(
    sleepDate: MutableState<String>,
    wakeDate: MutableState<String>,
    sleepTime: MutableState<String>,
    wakeTime: MutableState<String>,
    sleepDuration: MutableState<String>,
    context: Context
) {
    val sleepDateTime = "${sleepDate.value} ${sleepTime.value}".toCalendar()
    val wakeDateTime = "${wakeDate.value} ${wakeTime.value}".toCalendar()

    if (wakeDateTime.before(sleepDateTime)) {
        Toast.makeText(context, "기상시간이 취침시간보다 빠릅니다", Toast.LENGTH_SHORT).show()
        sleepTime.value = ""
        wakeTime.value = ""
        sleepDuration.value = ""
    } else {
        val diff = wakeDateTime.timeInMillis - sleepDateTime.timeInMillis
        val hours = TimeUnit.MILLISECONDS.toHours(diff)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diff) - TimeUnit.HOURS.toMinutes(hours)
        sleepDuration.value = String.format("%02d:%02d", hours, minutes)
    }
}
fun String.toCalendar(): Calendar {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val date = sdf.parse(this)
    return Calendar.getInstance().apply {
        time = date
    }
}
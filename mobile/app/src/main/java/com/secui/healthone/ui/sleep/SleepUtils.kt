package com.secui.healthone.ui.sleep

import androidx.compose.runtime.MutableState

fun calculateAngleFromTime(time: String): Float {
    val (hour, minute) = time.split(':').map(String::toInt)
    val totalMinutes = hour * 60 + minute
    return totalMinutes / (24f * 60f) * 360f - 90f
}

fun calculateSleepDuration(
    sleepTime: MutableState<String>,
    wakeTime: MutableState<String>,
    sleepDuration: MutableState<String>
) {
    val (sleepHours, sleepMinutes) = sleepTime.value.split(':').map(String::toInt)
    val (wakeHours, wakeMinutes) = wakeTime.value.split(':').map(String::toInt)

    val sleepMinutesTotal = sleepHours * 60 + sleepMinutes
    val wakeMinutesTotal = wakeHours * 60 + wakeMinutes

    val durationInMinutes = if (wakeMinutesTotal >= sleepMinutesTotal) {
        wakeMinutesTotal - sleepMinutesTotal
    } else {
        (24 * 60) - (sleepMinutesTotal - wakeMinutesTotal)
    }

    val durationHours = durationInMinutes / 60
    val durationMinutes = durationInMinutes % 60
    sleepDuration.value = String.format("%02d:%02d", durationHours, durationMinutes)
}

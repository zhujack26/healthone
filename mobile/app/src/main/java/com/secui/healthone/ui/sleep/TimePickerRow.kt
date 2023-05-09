package com.secui.healthone.ui.sleep

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TimePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.secui.healthone.repository.Sleep.SleepRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

@Composable
fun TimePickerRow(label: String, timeState: MutableState<String>, sleepRecords: MutableList<SleepRecord>) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$label: ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = timeState.value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = { coroutineScope.launch { showTimePicker(context, timeState, sleepRecords) } }) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
    }
}

suspend fun showTimePicker(context: Context, timeState: MutableState<String>, sleepRecords: MutableList<SleepRecord>) {
    val calendar = Calendar.getInstance()

    val timeSetListener = TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
        val newTime = String.format("%02d:%02d", hourOfDay, minute)
        if (isValidTime(newTime, sleepRecords, context)) {
            timeState.value = newTime
        }
    }

    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
    val currentMinute = calendar.get(Calendar.MINUTE)

    withContext(Dispatchers.Main) {
        TimePickerDialog(context, timeSetListener, currentHour, currentMinute, true).show()
    }
}

fun isValidTime(newTime: String, sleepRecords: MutableList<SleepRecord>, context: Context): Boolean {
    // Perform the same validation as mentioned in the previous response

    // Check if the new time is in the future
    val currentTime = Calendar.getInstance()
    val newTimeCalendar = Calendar.getInstance().apply {
        val (hour, minute) = newTime.split(":").map(String::toInt)
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }

    if (newTimeCalendar.after(currentTime)) {
        // Show "미래 시간을 입력할 수 없습니다" alert
        Toast.makeText(context, "미래 시간을 입력할 수 없습니다", Toast.LENGTH_SHORT).show()
        return false
    }

    // Check if the new time is overlapping with existing records
    for (record in sleepRecords) {
        if (newTime == record.sleepTime || newTime == record.wakeTime) {
            // Show "시간이 중복됩니다" alert
            Toast.makeText(context, "시간이 중복됩니다", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    return true
}
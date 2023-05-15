package com.secui.healthone.ui.sleep

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.secui.healthone.R
import com.secui.healthone.data.Sleep.SleepRecord
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun TimePickerRow(dateState: MutableState<String>, timeState: MutableState<String>, selectedTime: MutableState<String>, buttonCheck: MutableState<Boolean>) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val selectedDate = Calendar.getInstance()
    val sleepRecords = mutableListOf<SleepRecord>()

    LaunchedEffect(dateState.value, timeState.value) {
        val selectedDateTimeParts = selectedTime.value.split(" ")
        if (selectedDateTimeParts.size == 2) {
            dateState.value = selectedDateTimeParts[0]
            timeState.value = selectedDateTimeParts[1]
        }
    }

    if (buttonCheck.value) {
        dateState.value = ""
        timeState.value = ""
        buttonCheck.value = false
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = R.drawable.baseline_hotel_24), contentDescription = "Label Icon")
        Text(
            text = "${dateState.value} ${timeState.value}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = {
            coroutineScope.launch {
                showDatePicker(context, selectedDate, dateState, timeState, sleepRecords, selectedTime)
            }
        }) {
            Icon(painter = painterResource(id = R.drawable.baseline_schedule_24), contentDescription = "Edit")
        }
    }
}

fun showDatePicker(context: Context, selectedDate: Calendar, dateState: MutableState<String>, timeState: MutableState<String>, sleepRecords: MutableList<SleepRecord>, selectedTime: MutableState<String>) {
    val calendar = Calendar.getInstance()

    val previousDay = selectedDate.clone() as Calendar
    previousDay.add(Calendar.DAY_OF_MONTH, -1)

    val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        calendar.set(year, month, dayOfMonth)
        val newDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
        dateState.value = newDate
        selectedTime.value = "$newDate ${timeState.value}"

        // Call TimePicker here after the DatePickerDialog is dismissed
        showTimePicker(context, selectedDate, dateState, timeState, sleepRecords, selectedTime)
    }

    DatePickerDialog(context, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).apply {
        datePicker.minDate = previousDay.timeInMillis
        datePicker.maxDate = selectedDate.timeInMillis
        show()
    }
}

fun showTimePicker(context: Context, selectedDate: Calendar, dateState: MutableState<String>, timeState: MutableState<String>, sleepRecords: MutableList<SleepRecord>, selectedTime: MutableState<String>): Boolean {
    var result = true  // 수정 필요한 부분

    val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
        selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
        selectedDate.set(Calendar.MINUTE, minute)

        val newTime = String.format("%02d:%02d", hourOfDay, minute)
        if (isValidTime(selectedDate, newTime, sleepRecords, context)) {
            timeState.value = newTime
            selectedTime.value = "${dateState.value} $newTime"
        } else {
            result = false  // 수정 필요한 부분
        }
    }

    TimePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, timeSetListener, selectedDate.get(Calendar.HOUR_OF_DAY), selectedDate.get(Calendar.MINUTE), true).apply {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setOnCancelListener {
            result = false  // 수정 필요한 부분
        }
        show()
    }

    return result  // 수정 필요한 부분
}

fun isValidTime(selectedDate: Calendar, newTime: String, sleepRecords: MutableList<SleepRecord>, context: Context): Boolean {
    // Check if the new time is in the future
    val currentTime = Calendar.getInstance()

    val newTimeCalendar = selectedDate.apply {
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
        if (newTime == record.startSleepTime || newTime == record.endSleepTime) {
            // Show "시간이 중복됩니다" alert
            Toast.makeText(context, "시간이 중복됩니다", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    return true
}
package com.secui.healthone.ui.sleep

import android.app.TimePickerDialog
import android.content.Context
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun NowPickerRow(label: String, timeState: MutableState<String>, dateState: MutableState<String>) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$label: ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${dateState.value} ${timeState.value}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = { coroutineScope.launch { showNowTimePicker(context, timeState, dateState) } }) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
    }
}

suspend fun showNowTimePicker(context: Context, timeState: MutableState<String>, dateState: MutableState<String>) {
    val time = withContext(Dispatchers.Main) {
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            timeState.value = String.format("%02d:%02d", hourOfDay, minute)
        }
        TimePickerDialog(
            context,
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
            timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).apply {
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            show()
        }
    }
    // Set the date to today's date
    val today = Calendar.getInstance()
    dateState.value = String.format(
        "%04d-%02d-%02d",
        today.get(Calendar.YEAR),
        today.get(Calendar.MONTH) + 1,
        today.get(Calendar.DAY_OF_MONTH)
    )
}

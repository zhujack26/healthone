package com.secui.healthone.ui.mealplanpage

import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog


@Composable
fun DatePickerDialog(
    showDialog: Boolean,
    initialDate: Calendar,
    onDateSelected: (Calendar) -> Unit,
    onDismissRequest: () -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column {
                AndroidView(factory = { context ->
                    val datePicker = DatePicker(context).apply {
                        setBackgroundColor(android.graphics.Color.WHITE)
                    }
                    datePicker.init(
                        initialDate.get(Calendar.YEAR),
                        initialDate.get(Calendar.MONTH),
                        initialDate.get(Calendar.DAY_OF_MONTH)
                    ) { _, year, month, dayOfMonth ->
                        val newDate = Calendar.getInstance().apply {
                            set(Calendar.YEAR, year)
                            set(Calendar.MONTH, month)
                            set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        }
                        onDateSelected(newDate)
                    }
                    datePicker
                })
            }
        }
    }
}

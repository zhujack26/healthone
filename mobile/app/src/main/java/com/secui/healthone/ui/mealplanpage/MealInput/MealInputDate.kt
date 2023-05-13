package com.secui.healthone.ui.mealplanpage.MealInput

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.secui.healthone.constant.AppColors
import com.secui.healthone.ui.mealplanpage.DatePickerDialog
import java.time.LocalDateTime
import java.util.Calendar
import java.time.ZoneId

@Composable
fun MealInputDate(onIntervalAndDateSelected: (String, LocalDateTime) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    val initialDate = Calendar.getInstance()
    val selectedDate = rememberSaveable { mutableStateOf(initialDate) }
    var selectedInterval by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Select Date")
            }

            Text(
                text = String.format("%d.%02d.%02d", selectedDate.value.get(Calendar.YEAR), selectedDate.value.get(Calendar.MONTH) + 1, selectedDate.value.get(Calendar.DAY_OF_MONTH)),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Spacer(
            modifier = Modifier
                .height(24.dp)
                .width(2.dp)
                .background(AppColors.mono200)
        )

        Spacer(modifier = Modifier.width(8.dp))

        MealInputTime { newInterval ->
            selectedInterval = newInterval
            onIntervalAndDateSelected(selectedInterval, selectedDate.value.toLocalDateTime())
        }
    }

    DatePickerDialog(
        showDialog = showDialog,
        initialDate = initialDate,
        onDateSelected = { newDate ->
            selectedDate.value = newDate
            onIntervalAndDateSelected(selectedInterval, selectedDate.value.toLocalDateTime())
            showDialog = false
        },
        onDismissRequest = {
            showDialog = false
        }
    )
}

fun Calendar.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(this.toInstant(), ZoneId.systemDefault())
}

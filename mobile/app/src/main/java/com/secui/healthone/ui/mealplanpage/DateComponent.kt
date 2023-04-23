package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import android.icu.util.Calendar

@Composable
fun DateComponent() {
    var showDialog by remember { mutableStateOf(false) }
    val initialDate = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf(initialDate) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 이전 날짜로 가는 화살표
        IconButton(onClick = {
            selectedDate.value = (selectedDate.value.clone() as Calendar).apply { add(Calendar.DAY_OF_MONTH, -1) }
        }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Previous Date")
        }

        // 달력 아이콘과 날짜를 가운데에 배치
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 달력 아이콘
            IconButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Select Date")
            }

            // 선택된 날짜 표시
            Text(
                text = "${selectedDate.value.get(Calendar.YEAR)}.${selectedDate.value.get(Calendar.MONTH) + 1}.${selectedDate.value.get(Calendar.DAY_OF_MONTH)}",
                textAlign = TextAlign.Center
            )
        }

        // 다음 날짜로 가는 화살표
        IconButton(onClick = {
            selectedDate.value = (selectedDate.value.clone() as Calendar).apply { add(Calendar.DAY_OF_MONTH, 1) }
        }) {
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Next Date")
        }
    }

    DatePickerDialog(
        showDialog = showDialog,
        initialDate = initialDate,
        onDateSelected = { newDate ->
            // 날짜 선택 이벤트 처리
            selectedDate.value = newDate
            showDialog = false
        },
        onDismissRequest = {
            showDialog = false
        }
    )
}

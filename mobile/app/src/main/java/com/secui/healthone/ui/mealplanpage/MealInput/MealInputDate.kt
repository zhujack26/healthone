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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.ui.mealplanpage.DatePickerDialog
import java.time.LocalDateTime
import java.util.Calendar
import java.time.ZoneId
@Composable
fun MealInputDate(onIntervalAndDateSelected: (String, LocalDateTime) -> Unit) { // 파라미터 타입 변경
    var showDialog by remember { mutableStateOf(false) }
    val initialDate = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf(initialDate) }
    var selectedInterval by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
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

        // 세로선 왼쪽 간격 추가
        Spacer(modifier = Modifier.width(8.dp))

        // 세로선 추가
        Spacer(
            modifier = Modifier
                .height(24.dp)
                .width(2.dp)
                .background(AppColors.mono200)
        )

        // 세로선 오른쪽 간격 추가
        Spacer(modifier = Modifier.width(8.dp))

        // 시간 입력 컴포넌트 추가
        MealInputTime { newInterval ->
            selectedInterval = newInterval
            onIntervalAndDateSelected(selectedInterval, selectedDate.value.toLocalDateTime())
        }
    }

    DatePickerDialog(
        showDialog = showDialog,
        initialDate = initialDate,
        onDateSelected = { newDate ->
            // 날짜 선택 이벤트 처리
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
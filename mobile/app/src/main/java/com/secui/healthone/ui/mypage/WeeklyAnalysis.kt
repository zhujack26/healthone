package com.secui.healthone.ui.mypage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.secui.healthone.constant.AppColors
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

@Composable
fun WeeklyAnalysis() {
    val currentWeek = remember { getWeekRange(LocalDate.now()) }
    val textColor = AppColors.black
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        )
        {
            Text(
                text = "주간 분석",
                color = textColor
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = currentWeek,
                color = textColor
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "평균 활동 시간 ----------------------------------50분",
                color = textColor
            )
        }
    }
}

private fun getWeekRange(date: LocalDate): String {
    val weekStart = date.with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1)
    val weekEnd = weekStart.plusDays(6)
    val formatter = DateTimeFormatter.ofPattern("M월 d일")
    return "${weekStart.format(formatter)}~${weekEnd.format(formatter)}"
}
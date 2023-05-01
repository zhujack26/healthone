package com.secui.healthone.ui.mypage

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.secui.healthone.ui.common.AppColors
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

@Composable
fun WeeklyAnalysis() {
    val currentWeek = remember { getWeekRange(LocalDate.now()) }
    Column {
        Text(text = "주간 분석",
            color = AppColors.black)
        Text(text = currentWeek,
            color = AppColors.black)
        Text(text = "평균 활동 시간 ----------------------------------50분",
            color = AppColors.black)
    }
}

@Preview
@Composable
fun PreviewWeeklyAnalysis() {
    WeeklyAnalysis()
}

private fun getWeekRange(date: LocalDate): String {
    val weekStart = date.with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1)
    val weekEnd = weekStart.plusDays(6)
    val formatter = DateTimeFormatter.ofPattern("M월 d일")
    return "${weekStart.format(formatter)}~${weekEnd.format(formatter)}"
}
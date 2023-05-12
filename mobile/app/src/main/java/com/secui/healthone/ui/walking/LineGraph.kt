package com.secui.healthone.ui.walking

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.constant.AppColors
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.text.style.TextAlign
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun LineGraph(steps: List<Int>) {
    val lineColor = AppColors.green500
    val lineWidth = 4.dp

    Column {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(16.dp)
        ) {
            val maxValue = steps.maxOrNull() ?: 1
            val stepWidth = size.width / (steps.size - 1)

            for (i in 1 until steps.size) {
                val startX = stepWidth * (i - 1)
                val startY = size.height * (1 - steps[i - 1].toFloat() / maxValue)
                val endX = stepWidth * i
                val endY = size.height * (1 - steps[i].toFloat() / maxValue)

                drawLine(
                    start = Offset(x = startX, y = startY),
                    end = Offset(x = endX, y = endY),
                    color = lineColor,
                    strokeWidth = lineWidth.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val dateFormatter = DateTimeFormatter.ofPattern("M/d")
            val today = LocalDate.now()
            val dateList = (0 until steps.size).map { today.minusDays(it.toLong()) }

            dateList.reversed().forEachIndexed { index, date ->
                Text(
                    text = date.format(dateFormatter),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.Bottom)
                )
            }
        }
    }
}
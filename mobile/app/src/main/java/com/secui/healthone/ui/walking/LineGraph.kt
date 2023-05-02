package com.secui.healthone.ui.walking

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun LineGraph(steps: List<Int>) {
    val lineColor = MaterialTheme.colors.primary
    val lineWidth = 4.dp

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
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
}
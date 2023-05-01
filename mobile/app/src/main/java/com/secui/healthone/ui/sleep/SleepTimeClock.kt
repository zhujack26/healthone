package com.secui.healthone.ui.sleep

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.secui.healthone.data.Sleep.SleepTime
import androidx.compose.ui.graphics.StrokeCap

@Composable
fun SleepTimeClock(sleepTime: SleepTime, wakeTime: SleepTime, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val color = Color(0xFF3F51B5)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = size.minDimension / 30f
            val radius = size.minDimension / 2 - strokeWidth / 2

            // Draw clock background
            drawCircle(
                color = color.copy(alpha = 0.2f),
                style = Stroke(width = strokeWidth)
            )

            // Draw sleep to wake arc
            val startAngle = timeToAngle(sleepTime) - 90f
            val sweepAngle = timeToAngle(wakeTime) - timeToAngle(sleepTime)
            drawArc(
                color = color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
    }
}

private fun DrawScope.timeToAngle(time: SleepTime): Float {
    return (time.timetoFloat() % 24) * 360f / 24f
}
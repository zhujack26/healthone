package com.secui.healthone.ui.sleep

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.secui.healthone.constant.AppColors
import com.secui.healthone.data.Sleep.SleepRecord
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SleepTimeClock(sleepRecords: List<SleepRecord>) {
    Canvas(modifier = Modifier.size(200.dp)) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.width / 2

        // 기본 회색 원 그리기
        drawCircle(
            color = Color.LightGray,
            center = center,
            radius = radius
        )

        // 수면 기록 별로 녹색 원 그리기
        sleepRecords.forEach { sleepRecord ->
            Log.d(
                "Clock",
                "Sleep record : ${sleepRecord.startSleepTime} - ${sleepRecord.endSleepTime}"
            )
            val sleepTime = sleepRecord.startSleepTime
            val wakeTime = sleepRecord.endSleepTime
            val sleepAngle = if (sleepTime.isNotEmpty()) sleepTimeToAngle(sleepTime) else 0f
            val wakeAngle = if (wakeTime.isNotEmpty()) sleepTimeToAngle(wakeTime) else 0f


            val sweepAngle =
                if (wakeAngle >= sleepAngle) wakeAngle - sleepAngle else 360 + wakeAngle - sleepAngle

            drawPath(
                color = AppColors.green200,
                path = Path().apply {
                    moveTo(center.x, center.y)
                    arcTo(
                        rect = Rect(
                            left = center.x - radius,
                            top = center.y - radius,
                            right = center.x + radius,
                            bottom = center.y + radius
                        ),
                        startAngleDegrees = sleepAngle,
                        sweepAngleDegrees = sweepAngle,
                        forceMoveTo = false
                    )
                    lineTo(center.x, center.y)
                    close()
                }
            )
        }

        // 시간 텍스트와 점 그리기
        val textPaint = Paint().asFrameworkPaint().apply {
            color = Color.Black.toArgb()
            textSize = 30f
        }

        val dotPaint = Paint().asFrameworkPaint().apply {
            color = Color.Black.toArgb()
        }

        val dotRadius = 5f
        val innerRadius = radius * 0.85f

        for (i in 1..24) {
            val angle = (i * 360f / 24f) - 90f
            val position = Offset(                x = center.x + innerRadius * cos(Math.toRadians(angle.toDouble())).toFloat(),
                y = center.y + innerRadius * sin(Math.toRadians(angle.toDouble())).toFloat()
            )

            if (i == 6 || i == 12 || i == 18 || i == 24) {
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawText(
                        "$i",
                        position.x - textPaint.textSize / 2,
                        position.y + textPaint.textSize / 2,
                        textPaint
                    )
                }
            } else {
                drawCircle(
                    color = Color.Black,
                    center = position,
                    radius = dotRadius
                )
            }
        }
    }
}

private fun sleepTimeToAngle(dateTime: String): Float {
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    val parsedDateTime = LocalDateTime.parse(dateTime, formatter)
    val hour = parsedDateTime.hour
    val minute = parsedDateTime.minute
    val hoursInDecimal = hour + minute / 60f
    return (hoursInDecimal * 360f / 24f) - 90f
}


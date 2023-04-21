package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun CircularGraph(intakeCalories: Int, burnedCalories: Int, totalCalories: Int, recommendedCalories: Int, modifier: Modifier = Modifier) {
    val graphSize = 300.dp
    val strokeWidth = 45.dp
    val graphColors = listOf(Color(0xFFFFA726), Color(0xFFE53935))

    Box(modifier = modifier) {
        Canvas(modifier = Modifier
            .size(width = graphSize, height = graphSize)
            .offset(y = (graphSize * 0f))
        ) {
            val circleRadius = (size.minDimension - strokeWidth.toPx()) / 2
            val sweepAngle = 180f * (totalCalories.toFloat() / recommendedCalories.toFloat()).coerceAtMost(1f)

            val gradient = Brush.linearGradient(
                colors = graphColors,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f)
            )

            drawArc(
                brush = gradient,
                startAngle = 180f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(
                    (size / 2.0f).width - circleRadius,
                    (size / 2.0f).height - circleRadius
                ),
                size = size.copy(width = circleRadius * 2, height = circleRadius * 2),
                style = Stroke(width = strokeWidth.value, cap = StrokeCap.Round)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.matchParentSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "총 칼로리",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$totalCalories kcal",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(40.dp))

                // 섭취 칼로리 - 소모 칼로리 = 총 칼로리 텍스트
                Text(
                    text = "섭취 칼로리 (${intakeCalories}kcal) - 소모 칼로리 (${burnedCalories}kcal)\n= 총 칼로리 (${totalCalories}kcal)",
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light, color = Color(0xFF827E7E)),
                    textAlign = TextAlign.Center // 가운데 정렬
                )

                // 총 칼로리에 따른 상태 텍스트 컴포넌트
                CalorieStatus()
            }
        }
    }
}

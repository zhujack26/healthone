package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularGraph(totalCalories: Int, recommendedCalories: Int, modifier: Modifier = Modifier) {
    val graphSize = 300.dp
    val strokeWidth = 45.dp
    val graphColors = listOf(Color(0xFFFFA726), Color(0xFFE53935))

    Box(modifier = modifier.size(graphSize)) {
        Canvas(modifier = Modifier.matchParentSize()) {
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
                    (size / 2.0f).width - circleRadius.toFloat(),
                    (size / 2.0f).height - circleRadius.toFloat()
                ),
                size = size,
                style = Stroke(width = strokeWidth.value, cap = StrokeCap.Round)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.matchParentSize()
        ) {
            Text(
                text = "$totalCalories kcal",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview
@Composable
fun CircularGraphPreview() {
    CircularGraph(totalCalories = 3300, recommendedCalories = 3500)
}

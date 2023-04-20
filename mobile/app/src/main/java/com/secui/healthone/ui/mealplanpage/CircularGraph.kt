package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularGraph(totalCalories: Int, modifier: Modifier = Modifier) {
    val graphSize = 150.dp
    val strokeWidth = 15.dp
    val graphColor = Color.Red

    Box(modifier = modifier.size(graphSize)) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val circleRadius = (size.minDimension - strokeWidth.toPx()) / 2
            val halfCircleAngle = 180f

            drawArc(
                color = graphColor,
                startAngle = 180f,
                sweepAngle = halfCircleAngle,
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
    CircularGraph(totalCalories = 3300)
}

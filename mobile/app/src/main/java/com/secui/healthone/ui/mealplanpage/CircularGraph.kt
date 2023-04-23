package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
    val backgroundColor = Color(0xFFD3D3D3) // 옅은 회색 배경 색상

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .size(width = graphSize, height = graphSize)
                .offset(y = (graphSize * 0f))
        ) {
            val circleRadius = (size.minDimension - strokeWidth.toPx()) / 2

            // 배경용 회색 그래프 그리기
            drawArc(
                color = backgroundColor,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(
                    (size / 2.0f).width - circleRadius,
                    (size / 2.0f).height - circleRadius
                ),
                size = size.copy(width = circleRadius * 2, height = circleRadius * 2),
                style = Stroke(width = strokeWidth.value, cap = StrokeCap.Butt)
            )

            val sweepAngle =
                180f * (totalCalories.toFloat() / recommendedCalories.toFloat()).coerceAtMost(1f)
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
                style = Stroke(width = strokeWidth.value, cap = StrokeCap.Butt)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .matchParentSize()
                .offset(y = 40.dp) // 박스의 위치를 아래로 내립니다.
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "총 칼로리",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$totalCalories kcal",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(20.dp))

                // 섭취 칼로리 - 소모 칼로리 = 총 칼로리 텍스트
                Text(
                    text = "섭취 칼로리 (${intakeCalories}kcal) - 소모 칼로리 (${burnedCalories}kcal)\n= 총 칼로리 (${totalCalories}kcal)",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF827E7E)
                    ),
                    textAlign = TextAlign.Center // 가운데 정렬
                )

                // 총 칼로리에 따른 상태 텍스트 컴포넌트
                CalorieStatus()

                Spacer(modifier = Modifier.height(16.dp)) // 박스와 텍스트 사이의 공간을 추가합니다.

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(4.dp))
                        .padding(8.dp)
                        .width(500.dp) // 고정 너비 설정
                        .height(48.dp) // 고정 높이 설정
                ) {
                    Text(
                        text = "권장 섭취 칼로리 2,800 ~ 3,100 \n 운동으로 200kcal만큼 소모해주세요",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center // 가운데 정렬
                    )
                }
            }
        }
    }
}
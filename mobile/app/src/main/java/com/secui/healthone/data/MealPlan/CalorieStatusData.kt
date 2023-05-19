package com.secui.healthone.data.MealPlan

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString

data class CalorieStatusData(
    val status: String,
    val emoji: String,
    val textColor: Color,
    val statusText: String
)

@Composable
fun CalorieStatus(totalCalories: Int, recommendedCalories: Int): CalorieStatusData {
    val lowerBound = (recommendedCalories * 0.95).toInt()
    val upperBound = (recommendedCalories * 1.05).toInt()

    return when {
        totalCalories < lowerBound -> {
            CalorieStatusData(
                status = "부족",
                emoji = "😞",
                textColor = Color.Blue,
                statusText = "권장 섭취 칼로리 $lowerBound kcal ~ $upperBound kcal \n ${(lowerBound - totalCalories)}kcal 만큼 더 먹을 수 있어요!"
            )
        }
        totalCalories > upperBound -> {
            CalorieStatusData(
                status = "초과",
                emoji = "😰",
                textColor = Color.Red,
                statusText = "권장 섭취 칼로리 $lowerBound kcal ~ $upperBound kcal \n 운동으로 ${(totalCalories - upperBound)}kcal 만큼 소모해주세요!"
            )
        }
        else -> {
            CalorieStatusData(
                status = "적정",
                emoji = "😊",
                textColor = Color.Green,
                statusText = "권장 섭취 칼로리 $lowerBound kcal ~ $upperBound kcal \n 오늘의 적정치를 달성했습니다"
            )
        }
    }
}

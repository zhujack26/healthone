package com.secui.healthone.ui.mealplanpage

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CalorieStatus(totalCalories: Int, recommendedCalories: Int) {
    val status: String
    val emoji: String
    val textColor: Color
    val lowerBound = recommendedCalories * 0.95
    val upperBound = recommendedCalories * 1.05

    when {
        totalCalories < lowerBound -> {
            status = "부족"
            emoji = "😞"
            textColor = Color.Blue
        }
        totalCalories < upperBound -> {
            status = "초과"
            emoji = "😰"
            textColor = Color.Red
        }
        else -> {
            status = "적정"
            emoji = "😊"
            textColor = Color.Green
        }
    }

    Text("$emoji $status", color = textColor)
}

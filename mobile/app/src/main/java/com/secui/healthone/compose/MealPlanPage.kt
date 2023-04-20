package com.secui.healthone.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.secui.healthone.dataclass.Meal
import com.secui.healthone.ui.mealplanpage.CalorieStatus
import com.secui.healthone.ui.mealplanpage.DateComponent
import com.secui.healthone.ui.mealplanpage.CircularGraph
import com.secui.healthone.ui.mealplanpage.MealCard
import com.secui.healthone.ui.mealplanpage.TopBar

@Composable
fun MealPlanPage() {
    val meal = Meal("Sample Meal", "Sample Description", 300)

    Column {
        // TopBar 컴포넌트
        TopBar()

        // 날짜 컴포넌트
        DateComponent()

        // 건강 기록 및 일간 텍스트
        Text("건강 기록")
        Text("일간")

        // 가로 막대 그래프 컴포넌트
        CircularGraph(totalCalories = 3300)

        // 총 칼로리 텍스트
        Text("총 칼로리")

        // 3300kcal 텍스트
        Text("3300kcal")

        // 섭취 칼로리 - 소모 칼로리 = 총 칼로리 텍스트
        Text("섭취 칼로리 - 소모 칼로리 = 총 칼로리")

        // 총 칼로리에 따른 상태 텍스트 컴포넌트
        CalorieStatus()

        // MealCard 컴포넌트
        MealCard(meal)
    }
}

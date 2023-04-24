package com.secui.healthone.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.secui.healthone.dataclass.Meal
import com.secui.healthone.repository.CaloriesData
import com.secui.healthone.repository.fetchCaloriesData
import com.secui.healthone.ui.mealplanpage.*
import kotlinx.coroutines.launch

@Composable
fun MealPlanPage() {
    val meal = Meal("Sample Meal", "Sample Description", 300)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var caloriesData by remember { mutableStateOf<CaloriesData?>(null) }

    LaunchedEffect(Unit) {
        scope.launch {
            caloriesData = fetchCaloriesData()
        }
    }

    if (caloriesData != null) {
        val updatedIntakeCalories = caloriesData!!.intakeCalories
        val updatedBurnedCalories = caloriesData!!.burnedCalories
        val updatedTotalCalories = caloriesData!!.totalCalories

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TopBar 컴포넌트
            TopBar()

            // 날짜 컴포넌트
            DateComponent()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 건강 기록 텍스트
                Text("건강 기록", modifier = Modifier.align(Alignment.CenterVertically))

                // 일간, 주간, 월간 선택 컴포넌트
                TimeIntervalSelector()
            }


            // 그래프 컴포넌트
            CircularGraph(totalCalories = updatedTotalCalories, recommendedCalories = 4540)

            // 총 칼로리 텍스트
            Text("총 칼로리")

            // 3300kcal 텍스트
            Text("${updatedTotalCalories}kcal")

            // 섭취 칼로리 - 소모 칼로리 = 총 칼로리 텍스트
            Text("섭취 칼로리 (${updatedIntakeCalories}kcal) - 소모 칼로리 (${updatedBurnedCalories}kcal) = 총 칼로리 (${updatedTotalCalories}kcal)")

            // 총 칼로리에 따른 상태 텍스트 컴포넌트
            CalorieStatus()

            // MealCard 컴포넌트
            MealCard(meal)
        }
    } else {
        // 데이터를 불러오는 동안 표시할 로딩 인디케이터 등을 여기에 작성합니다.
    }
}

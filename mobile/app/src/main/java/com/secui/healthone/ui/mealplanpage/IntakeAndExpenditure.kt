package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.secui.healthone.data.MealPlan.Exercise
import com.secui.healthone.data.MealPlan.Meal
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.util.PageRoutes

@Composable
fun IntakeAndExpenditure(navController: NavController) {
    val meals = listOf(
        Meal(
            "아침",
            "된장찌개",
            300,
        ),
        Meal(
            "아침",
            "김치찌개",
            300,
        ),
        Meal("점심", "김치찌개", 450)
    )

    val exercises = listOf(
        Exercise("Running", "30 minutes", 200),
        Exercise("Yoga", "1 hour", 250)
    )

    val mealGroups = meals.groupBy { it.name }

    var selectedIndex by remember { mutableStateOf(0) }

    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(if (selectedIndex == 0) AppColors.mono50 else AppColors.mono200)
                    .clickable { selectedIndex = 0 }
                    .padding(8.dp)
            ) {
                Text("섭취 내역", color = if (selectedIndex == 0) Color.Black else Color.White)
            }
            Box(
                modifier = Modifier.weight(1f)
                    .background(if (selectedIndex == 1) AppColors.mono50 else AppColors.mono200)
                    .clickable { selectedIndex = 1 }
                    .padding(8.dp)
            ) {
                Text("소모 내역", color = if (selectedIndex == 1) Color.Black else Color.White)
            }
        }

        when (selectedIndex) {
            0 -> {
                mealGroups.forEach { (name, mealList) ->
                    MealCard(mealList, name)
                }
                Button(
                    onClick = { navController.navigate(PageRoutes.MealInput.route)},
                    colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("식단 기록하기")
                }
            }
            1 -> {
                exercises.forEach { exercise -> ExerciseCard(exercise) }
                Button(
                    onClick = { navController.navigate(PageRoutes.ExerciseInput.route) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("운동 기록하기")
                }
            }
        }
    }
}

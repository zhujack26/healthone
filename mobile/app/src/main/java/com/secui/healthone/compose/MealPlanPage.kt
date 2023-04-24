package com.secui.healthone.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.secui.healthone.data.Meal
import com.secui.healthone.repository.CaloriesData
import com.secui.healthone.repository.fetchCaloriesData
import com.secui.healthone.ui.mealplanpage.*
import kotlinx.coroutines.launch
import androidx.compose.material.Card
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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
        val updatedRecommendedCalories = caloriesData!!.recommendedCalories

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TopBar()
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DateComponent()
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("건강 기록", modifier = Modifier.align(Alignment.CenterVertically),
                            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        )
                        TimeIntervalSelector()
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularGraph(
                            intakeCalories = updatedIntakeCalories,
                            burnedCalories = updatedBurnedCalories,
                            totalCalories = updatedTotalCalories,
                            recommendedCalories = updatedRecommendedCalories,
                            modifier = Modifier.padding(bottom = 1.dp)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                    Column(modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IntakeAndExpenditure()
                    }
                }
            }
        }
    } else {
// 로딩 인디케이터
    }
}


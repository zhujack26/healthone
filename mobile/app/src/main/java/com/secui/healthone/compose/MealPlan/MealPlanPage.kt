package com.secui.healthone.compose

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.secui.healthone.repository.CaloriesData
import com.secui.healthone.repository.fetchCaloriesData
import com.secui.healthone.ui.mealplanpage.*
import kotlinx.coroutines.launch
import androidx.compose.material.Card
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.data.MealPlan.CalorieStatus
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun MealPlanPage(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var caloriesData by remember { mutableStateOf<CaloriesData?>(null) }
    val initialDate = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf(initialDate) }
    var refreshGraph by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = selectedDate.value, key2 = refreshGraph) {
        scope.launch {
            val formattedDate = formatDate(selectedDate.value)
            caloriesData = fetchCaloriesData(formattedDate)
            refreshGraph = false
            // Log the API response
            Log.d("MealPlanPage", "API Response: $caloriesData")
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
                        DateComponent(
                            selectedDate = selectedDate,
                            onDateChanged = { newDate ->
                                selectedDate.value = newDate
                            }
                        )

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
                        Text(
                            "건강 기록", modifier = Modifier.align(Alignment.CenterVertically),
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

                        // Create the CalorieStatus object outside of the remember function
                        val calorieStatusData = CalorieStatus(
                            totalCalories = updatedTotalCalories,
                            recommendedCalories = updatedRecommendedCalories
                        )

                        CircularGraph(
                            intakeCalories = updatedIntakeCalories,
                            burnedCalories = updatedBurnedCalories,
                            totalCalories = updatedTotalCalories,
                            recommendedCalories = updatedRecommendedCalories,
                            calorieStatusData = remember(updatedTotalCalories, updatedRecommendedCalories) { calorieStatusData },
                            modifier = Modifier.padding(bottom = 1.dp)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IntakeAndExpenditure(navController, selectedDate = selectedDate.value){
                            refreshGraph = true
                        }
                    }
                }
            }
        }
    } else {
        //로딩화면
    }
}

fun formatDate(calendar: Calendar): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(calendar.time)
}
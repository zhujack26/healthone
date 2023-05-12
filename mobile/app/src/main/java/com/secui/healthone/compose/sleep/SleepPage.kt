package com.secui.healthone.compose.sleep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.mealplanpage.DateComponent
import com.secui.healthone.ui.mealplanpage.TimeIntervalSelector
import com.secui.healthone.ui.sleep.CustomSleepGoal
import com.secui.healthone.ui.sleep.SleepRecords
import com.secui.healthone.viewmodel.SleepViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun SleepPage(navController: NavHostController, modifier: Modifier = Modifier) {
    val selectedSleepTime = remember { mutableStateOf("") }
    val selectedWakeTime = remember { mutableStateOf("") }
    val initialDate = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf(initialDate) }
    val sleepViewModel = remember { SleepViewModel() }

    val selectedDateString by remember {
        derivedStateOf {
            // Convert selectedDate to string in the required format here
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            sdf.format(selectedDate.value.time)
        }
    }

    LaunchedEffect(selectedDateString) {
        sleepViewModel.fetchSleepRecords(selectedDateString)
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DateComponent(
                        selectedDate = selectedDate,
                        onDateChanged = { newDate ->
                            selectedDate.value = newDate
                            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val dateStr = sdf.format(newDate.time)
                            sleepViewModel.fetchSleepRecords(dateStr)
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "수면 기록", modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    )
                    TimeIntervalSelector()
                }
            }
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                CustomSleepGoal(selectedSleepTime, selectedWakeTime, sleepViewModel.sleepRecords)
            }
            SleepRecords(sleepViewModel, selectedSleepTime, selectedWakeTime)
        }
    }
}

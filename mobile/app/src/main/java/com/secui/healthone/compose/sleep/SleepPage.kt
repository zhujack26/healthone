package com.secui.healthone.compose.sleep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.secui.healthone.constant.AppColors
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
    // 추가: buttonCheck state 생성
    val buttonCheck = remember { mutableStateOf(false) }

    val selectedSleepTime = remember { mutableStateOf("") }
    val selectedWakeTime = remember { mutableStateOf("") }
    val initialDate = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf(initialDate) }
    val sleepViewModel = remember { SleepViewModel() }
    val showInputFields = remember { mutableStateOf(sleepViewModel.sleepRecords.isEmpty()) }
    val selectedDateString by remember {
        derivedStateOf {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            sdf.format(selectedDate.value.time)
        }
    }
    val timeIntervals = listOf("일간", "주간")
    var selectedInterval by remember { mutableStateOf(timeIntervals.first()) }
    LaunchedEffect(selectedDateString) {
        sleepViewModel.fetchSleepRecords(selectedDateString)
        // 날짜가 변경될 때마다 buttonCheck를 true로 설정
        buttonCheck.value = true
    }

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
                            clearDateTime(selectedSleepTime, selectedWakeTime, buttonCheck) // 수정: buttonCheck 추가
                            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val dateStr = sdf.format(newDate.time)
                            sleepViewModel.fetchSleepRecords(dateStr)
                        }
                    )
                }
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) } // spacing after TimeIntervalSelector
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                CustomSleepGoal(selectedSleepTime, selectedWakeTime, sleepViewModel.sleepRecords, buttonCheck)
            }
        }
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (showInputFields.value) {
                    Button(
                        onClick = {
                            sleepViewModel.saveSleepRecord(selectedSleepTime.value, selectedWakeTime.value)
                            clearDateTime(selectedSleepTime, selectedWakeTime, buttonCheck) // 수정: buttonCheck 추가
                            // 저장 버튼을 클릭할 때마다 buttonCheck를 true로 설정
                            buttonCheck.value = true
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
                        modifier = Modifier.width(200.dp).align(Alignment.CenterHorizontally)
                    ) {
                        Text("저장")
                    }
                } else {
                    Button(
                        onClick = { showInputFields.value = true },
                        colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                    ) {
                        Text("수면 기록 추가")
                    }
                }
            }
        }

        items(sleepViewModel.sleepRecords.size) { index ->
            SleepRecords(sleepViewModel, index)
        }
    }
}

private fun clearDateTime(selectedSleepTime: MutableState<String>, selectedWakeTime: MutableState<String>, buttonCheck: MutableState<Boolean>) {
    if (buttonCheck.value) {
        selectedSleepTime.value = ""
        selectedWakeTime.value = ""
        buttonCheck.value = false // clearDateTime 함수가 실행된 후에는 buttonCheck를 다시 false로 설정
    }
}

package com.secui.healthone.compose.MealPlan

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.secui.healthone.ui.mealplanpage.ExerciseInput.ExerciseInputDate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.secui.healthone.data.MealPlan.AddExercise
import com.secui.healthone.data.MealPlan.ExerciseSearch
import com.secui.healthone.constant.AppColors
import com.secui.healthone.ui.mealplanpage.ExerciseInput.ExerciseSearchResults
import com.secui.healthone.ui.mealplanpage.MealInput.SearchBar
import com.secui.healthone.constant.PageRoutes
import com.secui.healthone.viewmodel.ExerciseViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ExerciseInputPage(navController: NavController) {
    val viewModel: ExerciseViewModel = viewModel()
    val searchResults: State<List<ExerciseSearch>> = viewModel.searchResults.observeAsState(emptyList())
    Log.d("ExerciseInputPage", "searchResults: ${searchResults.value}") // 로그 추가
    var showWarning by remember { mutableStateOf(false) }
    var searchTerm by remember { mutableStateOf("") }
    var selectedExerciseId by remember { mutableStateOf<Int?>(null) }
    val selectedDate = remember { mutableStateOf(LocalDateTime.now().withNano(0)) }
    val onExerciseSelected: (Int) -> Unit = { selectedId ->
        selectedExerciseId = selectedId
    }
    val coroutineScope = rememberCoroutineScope()
    val selectedExercise = remember { mutableStateOf<ExerciseSearch?>(null) }
    var ExerciseName by remember { mutableStateOf("") }
    var inputTimeText by remember { mutableStateOf("") }
    var inputKcalText by remember { mutableStateOf("") }
    var isDirectInput by remember { mutableStateOf(false) }
    val onSearchTermChanged: (String) -> Unit = { newTerm ->
        searchTerm = newTerm
        viewModel.searchExercise(newTerm)  // 검색 함수 호출
    }
    val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    val selectedDateString by remember { derivedStateOf { selectedDate.value.format(dateTimeFormatter) } }
    val onDirectInputButtonClick = {
        isDirectInput = true
        selectedExerciseId = -1
        selectedExercise.value = null
    }


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "운동 기록", modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    )
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
                    key(selectedDate) {
                        ExerciseInputDate { dateString, date ->
                            selectedDate.value = date
                        }
                    }
                }
            }
        }
        item {
            // 메뉴 검색 텍스트
            Text(
                text = "메뉴 검색",
                modifier = Modifier.padding(8.dp),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
        }
        item {
            // 검색창 컴포저블
            SearchBar("Exercise", searchTerm, onSearchTermChanged)
        }
        item {
            // 검색결과 컴포저블
            ExerciseSearchResults(
                results = viewModel.searchResults.value.orEmpty(),
                selectedId = selectedExercise.value?.no,  // 여기를 수정했습니다.
                onItemSelected = { exerciseId ->
                    selectedExercise.value = viewModel.searchResults.value?.find { it.no == exerciseId }
                    isDirectInput = false
                },
            )
        }
        item {
            if (showWarning) {
                Text(
                    text = "운동을 선택해주세요.",
                    modifier = Modifier.padding(8.dp),
                    color = AppColors.red200,
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }
        item {
            selectedExercise.value?.let { exercise ->
                Column {
                    Text("선택된 음식: ${exercise.name}")
                    Text("기본 시간: 60분")
                    Text("기본 칼로리: ${exercise.consumeKcal} g")

                    // 그램 입력 부분이 잘못되었다면 여기를 수정해주세요
                    var inputGrams by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = inputGrams,
                        onValueChange = { newValue ->
                            inputGrams = newValue
                            exercise.inputTimes = newValue.toIntOrNull() ?: 0
                        },
                        label = { Text("시간 입력") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    // 새로운 칼로리 계산 부분이 잘못되었다면 여기를 수정해주세요
                    val newCalories = if (exercise.inputTimes != 0) {
                        exercise.consumeKcal.toDouble() / 60 * exercise.inputTimes
                    } else {
                        0.0
                    }
                    Text(text = "새로운 칼로리: %.2f kcal".format(newCalories))
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        // 추가하기 버튼
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // 원 모양의 버튼 배경에 플러스 아이콘을 포함
                Button(
                    onClick = onDirectInputButtonClick,
                    shape = CircleShape,
                    modifier = Modifier.size(48.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                }

                // "추가하기" 텍스트
                Text("직접 추가하기", textAlign = TextAlign.Center, color = AppColors.green200)
            }
        }
        item {
            if (isDirectInput) {
                Column {
                    Text("음식 이름")
                    OutlinedTextField(
                        value = ExerciseName,
                        onValueChange = { newValue -> ExerciseName = newValue },
                        label = { Text("음식 이름 입력") },
                    )
                    Text("시간 입력(분)")
                    OutlinedTextField(
                        value = inputTimeText,
                        onValueChange = { newValue -> inputTimeText = newValue },
                        label = { Text("시간(분)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Text("소모 칼로리 입력")
                    OutlinedTextField(
                        value = inputKcalText,
                        onValueChange = { newValue -> inputKcalText = newValue },
                        label = { Text("칼로리") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            } else if (selectedExercise.value != null) {
                isDirectInput = false
            }
        }
        item {
            Button(
                onClick = {
                    if (selectedExercise.value != null || isDirectInput) {
                        if (isDirectInput) {
                            val exercise = AddExercise(
                                userNo = 1,
                                name = ExerciseName,
                                createTime = selectedDateString,
                                workTime = inputTimeText.toIntOrNull() ?: 0,
                                consumeCalorie = inputKcalText.toIntOrNull() ?: 0,
                                heartRate = 0,
                                bloodPressure = 0
                            )
                            coroutineScope.launch {
                                viewModel.addExercise(exercise)
                            }
                        } else {
                            val inputTime = selectedExercise.value!!.inputTimes
                            val inputKcal = (selectedExercise.value!!.consumeKcal.toDouble() / 60 * inputTime).toInt()
                            val exercise = AddExercise(
                                userNo = 1,
                                name = selectedExercise.value!!.name,
                                createTime = selectedDateString,
                                workTime = inputTime,
                                consumeCalorie = inputKcal,
                                heartRate = 0,
                                bloodPressure = 0
                            )
                            coroutineScope.launch {
                                viewModel.addExercise(exercise)
                            }
                        }
                        navController.navigate(PageRoutes.MealPlan.route)
                    } else {
                        showWarning = true
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 16.dp)
        ) {
                Text("저장")
            }
        }
    }
}
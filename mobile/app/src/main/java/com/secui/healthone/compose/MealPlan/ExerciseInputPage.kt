package com.secui.healthone.compose.MealPlan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.secui.healthone.data.MealPlan.Exercise
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.ui.mealplanpage.ExerciseInput.ExerciseSearchResults
import com.secui.healthone.ui.mealplanpage.MealInput.SearchBar

@Composable
fun ExerciseInputPage(navController: NavController) {
    val sampleExercise = listOf(
        Exercise(1, name = "걷기", 60, caloriesBurned = 100),
        Exercise(2, name = "수영", 60, caloriesBurned = 100),
        Exercise(3, name = "달리기", 60, caloriesBurned = 100)
    )

    var searchTerm by remember { mutableStateOf("") }
    val searchResults =
        sampleExercise.filter { exercise -> exercise.name.contains(searchTerm, ignoreCase = true) }
    var selectedExerciseId by remember { mutableStateOf(-1) }

    val onSearchTermChanged: (String) -> Unit = { newTerm ->
        searchTerm = newTerm
    }
    var showWarning by remember { mutableStateOf(false) }
    val onExerciseSelected: (Int) -> Unit = { selectedId ->
        selectedExerciseId = selectedId
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    ExerciseInputDate()
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
            ExerciseSearchResults(searchResults, selectedExerciseId, onExerciseSelected)
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
                    onClick = {
                        if (selectedExerciseId != -1) {
                            // 선택한 운동을 처리하거나 저장합니다.
                            // 예: navController.navigate()를 사용하여 다른 페이지로 이동하고 데이터를 전달할 수 있습니다.
                        } else {
                            // 경고 메시지를 표시하거나 다른 작업을 수행합니다.
                        }
                    },
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
    }
}
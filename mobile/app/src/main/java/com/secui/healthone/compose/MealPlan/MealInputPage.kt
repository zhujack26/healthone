package com.secui.healthone.compose.MealPlan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.secui.healthone.data.MealPlan.Food
import com.secui.healthone.ui.mealplanpage.MealInput.MealInputDate
import com.secui.healthone.ui.mealplanpage.MealInput.SearchBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.secui.healthone.constant.AppColors
import com.secui.healthone.ui.mealplanpage.MealInput.MealSearchResults
import com.secui.healthone.constant.PageRoutes
import com.secui.healthone.viewmodel.FoodViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.secui.healthone.data.MealPlan.Meal
import com.secui.healthone.data.MealPlan.MealType
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
@Composable
fun MealInputPage(navController: NavController) {
    val viewModel: FoodViewModel = viewModel()
    val searchResults: State<List<Food>> = viewModel.searchResults.observeAsState(emptyList())
    var showWarning by remember { mutableStateOf(false) }
    var searchTerm by remember { mutableStateOf("") }
    var selectedFoodId by remember { mutableStateOf(-1) }
    val selectedDate = remember { mutableStateOf(LocalDateTime.now().withNano(0)) }
    val hasMoreResults by viewModel.hasMoreResults.observeAsState(false)
    val onSearchTermChanged: (String) -> Unit = { newTerm ->
        searchTerm = newTerm
        if (searchTerm.isNotBlank()) {
            viewModel.searchFood(searchTerm, page = 1, size = 20)
        }
    }
    val coroutineScope = rememberCoroutineScope()
    val selectedFood = remember { mutableStateOf<Food?>(null) }
    var selectedInterval by remember { mutableStateOf("") }
//    val onFoodSelected: (Int) -> Unit = { selectedId ->
//        selectedFoodId = selectedId
//        isDirectInput = false // 이 부분을 추가합니다.
//    }

    val lazyListState = rememberLazyListState()
    val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    val selectedDateString by remember { derivedStateOf { selectedDate.value.format(dateTimeFormatter) } }
    var showCustomInput by remember { mutableStateOf(false) }
    var isDirectInput by remember { mutableStateOf(false) }
    val onDirectInputButtonClick = {
        isDirectInput = true
        selectedFoodId = -1
        selectedFood.value = null
    }
    var foodName by remember { mutableStateOf("") }
    var inputGramsText by remember { mutableStateOf("") }
    var inputKcalText by remember { mutableStateOf("") }

    LazyColumn(
        state = lazyListState,
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
                        "식단 기록", modifier = Modifier.align(Alignment.CenterVertically),
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
                        MealInputDate { interval, date ->
                            selectedInterval = interval
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
            SearchBar("Food", searchTerm, onSearchTermChanged)
        }
        item {
            // 검색결과 컴포저블
            MealSearchResults(
                searchResults = viewModel.searchResults.value.orEmpty(),
                selectedFoodId = selectedFood.value?.no,
                onFoodSelected = { foodId ->
                    selectedFood.value = viewModel.searchResults.value?.find { it.no == foodId }
                    isDirectInput = false // 이 부분을 추가합니다.
                },
            )
        }
        item {
            if (hasMoreResults) {
                Button(onClick = { viewModel.loadNextPage() }) {
                    Text("더보기")
                }
            }
        }
        item {
            if (showWarning) {
                Text(
                    text = "식단을 선택해주세요.",
                    modifier = Modifier.padding(8.dp),
                    color = AppColors.red200,
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }
        item {
            selectedFood.value?.let { food ->
                Column {
                    Text("선택된 음식: ${food.name}")
                    Text("기본 칼로리: ${food.kcal} kcal")
                    Text("기본 그램수: ${food.gram} g")

                    // 그램 입력 부분이 잘못되었다면 여기를 수정해주세요
                    var inputGrams by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = inputGrams,
                        onValueChange = { newValue ->
                            inputGrams = newValue
                            food.inputGrams = newValue.toIntOrNull() ?: 0
                        },
                        label = { Text("그램 입력") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    // 새로운 칼로리 계산 부분이 잘못되었다면 여기를 수정해주세요
                    val newCalories = if (food.inputGrams != 0) {
                        food.kcal.toDouble() / food.gram * food.inputGrams
                    } else {
                        0.0
                    }
                    Text(text = "새로운 칼로리: %.2f kcal".format(newCalories))
                }
            }
        }

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
                        value = foodName,
                        onValueChange = { newValue -> foodName = newValue },
                        label = { Text("음식 이름 입력") },
                    )
                    Text("그램 입력")
                    OutlinedTextField(
                        value = inputGramsText,
                        onValueChange = { newValue -> inputGramsText = newValue },
                        label = { Text("그램 입력") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Text("칼로리 입력")
                    OutlinedTextField(
                        value = inputKcalText,
                        onValueChange = { newValue -> inputKcalText = newValue },
                        label = { Text("칼로리 입력") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            } else if (selectedFood.value != null) {
                isDirectInput = false
            }
        }
        item {
            Button(
                onClick = {
                    if (selectedFood.value != null || isDirectInput) {
                        if (isDirectInput) {
                            val mealType = when (selectedInterval) {
                                "간식" -> MealType.SNACK
                                "점심" -> MealType.LUNCH
                                "저녁" -> MealType.DINNER
                                else -> MealType.BREAKFAST
                            }
                            val meal = Meal(
                                userNo = 1,
                                name = foodName,
                                createTime = selectedDateString,
                                mealType = mealType.toString(),
                                gram = inputGramsText.toIntOrNull() ?: 0,
                                kcal = inputKcalText.toIntOrNull() ?: 0
                            )
                            coroutineScope.launch {
                                viewModel.addMeal(meal)
                            }
                        } else {
                            val inputGrams = selectedFood.value!!.inputGrams
                            val inputKcal = (selectedFood.value!!.kcal.toDouble() / selectedFood.value!!.gram * inputGrams).toInt()
                            val mealType = when (selectedInterval) {
                                "간식" -> MealType.SNACK
                                "점심" -> MealType.LUNCH
                                "저녁" -> MealType.DINNER
                                else -> MealType.BREAKFAST
                            }
                            val meal = Meal(
                                userNo = 1,
                                name = selectedFood.value!!.name,
                                createTime = selectedDateString,
                                mealType = mealType.toString(),
                                gram = inputGrams,
                                kcal = inputKcal
                            )
                            coroutineScope.launch {
                                viewModel.addMeal(meal)
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
    LaunchedEffect(searchResults.value, hasMoreResults) {
        if (hasMoreResults && lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty()) {
            coroutineScope.launch {
                if (lazyListState.layoutInfo.visibleItemsInfo.last().index == searchResults.value.size - 1) {
                    viewModel.loadNextPage()
                }
            }
        }
    }
}
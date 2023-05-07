package com.secui.healthone.compose.MealPlan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
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
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.ui.mealplanpage.MealInput.MealSearchResults
import com.secui.healthone.util.PageRoutes
import com.secui.healthone.viewmodel.FoodViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.input.KeyboardType
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
    var selectedDate by remember { mutableStateOf(LocalDateTime.now().withNano(0)) }
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
    val onFoodSelected: (Int) -> Unit = { selectedId ->
        selectedFoodId = selectedId
    }
    val lazyListState = rememberLazyListState()
    val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    val selectedDateString = selectedDate.format(dateTimeFormatter)


    LazyColumn(
        state = lazyListState,
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
                    MealInputDate { interval, date ->
                        selectedInterval = interval
                        selectedDate = date
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

                    val newCalories = if (food.inputGrams != 0) {
                        food.kcal.toDouble() / food.gram * food.inputGrams
                    } else {
                        0.0
                    }
                    Text("새로운 칼로리: %.2f kcal".format(newCalories))
                }
            }
        }

        item {
            Button(
                onClick = {
                    if (selectedFood.value != null) {
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
                // 스크롤이 끝에 도달하면 다음 페이지를 로드합니다.
                if (lazyListState.layoutInfo.visibleItemsInfo.last().index == searchResults.value.size - 1) {
                    viewModel.loadNextPage()
                }
            }
        }
    }
}
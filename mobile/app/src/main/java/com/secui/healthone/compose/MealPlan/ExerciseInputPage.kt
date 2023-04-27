package com.secui.healthone.compose.MealPlan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import com.secui.healthone.data.MealPlan.Food
import com.secui.healthone.ui.mealplanpage.MealInput.MealInputDate
import com.secui.healthone.ui.mealplanpage.MealInput.SearchBar
import com.secui.healthone.ui.mealplanpage.MealInput.SearchResults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.util.PageRoutes

@Composable
fun ExerciseInputPage(navController: NavController) {
    val sampleFoods = listOf(
        Food(id = 1, name = "사과", servingSize = 100f, calories = 52f),
        Food(id = 2, name = "바나나", servingSize = 100f, calories = 89f),
        Food(id = 3, name = "포도", servingSize = 100f, calories = 67f)
    )

    var searchTerm by remember { mutableStateOf("") }
    val searchResults =
        sampleFoods.filter { food -> food.name.contains(searchTerm, ignoreCase = true) }
    var selectedFoodId by remember { mutableStateOf(-1) }

    val onSearchTermChanged: (String) -> Unit = { newTerm ->
        searchTerm = newTerm
    }

    val onFoodSelected: (Int) -> Unit = { selectedId ->
        selectedFoodId = selectedId
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
                    MealInputDate()
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
            SearchBar(searchTerm, onSearchTermChanged)
        }
        item {
            // 검색결과 컴포저블
            SearchResults(searchResults, selectedFoodId, onFoodSelected)
        }
        item {
            Button(
                onClick = { navController.navigate(PageRoutes.MealPlan.route)},
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
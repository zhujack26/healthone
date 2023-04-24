package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.secui.healthone.data.Meal

@Composable
fun MealCard(meal: Meal) {
    Card {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = meal.name)
            Text(text = meal.description)
            Text(text = "${meal.calories} kcal")
            Button(onClick = { /* 식단 추가하기 액션 */ }, modifier = Modifier.padding(8.dp)) {
                Text("수정하기")
            }
        }
    }
}
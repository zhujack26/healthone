package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.secui.healthone.data.Meal

@Composable
fun MealCard(meal: Meal) {
    Card {
        Column {
            Text(text = meal.name)
            Text(text = meal.description)
            Text(text = "${meal.calories} kcal")
        }
    }
}

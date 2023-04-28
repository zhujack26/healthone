package com.secui.healthone.ui.mealplanpage.ExerciseInput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.data.MealPlan.Exercise
import com.secui.healthone.ui.common.AppColors

@Composable
fun ExerciseSearchResults(
    results: List<Exercise>,
    selectedId: Int,
    onItemSelected: (Int) -> Unit
) {
    Column {
        results.forEach { exercise ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedId == exercise.id,
                    onClick = { onItemSelected(exercise.id) }
                )
                Text(
                    text = exercise.name,
                    modifier = Modifier.weight(2f),
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "${exercise.time} 분",
                    modifier = Modifier.weight(1f),
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "${exercise.caloriesBurned} kcal",
                    modifier = Modifier.weight(1f),
                    style = TextStyle(fontSize = 16.sp)
                )
            }
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(AppColors.mono200)
            )
        }
    }
}
package com.secui.healthone.ui.mealplanpage.ExerciseInput

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.constant.AppColors
import com.secui.healthone.data.MealPlan.ExerciseSearch

@Composable
fun ExerciseSearchResults(
    results: List<ExerciseSearch>,
    selectedId: Int?,
    onItemSelected: (Int) -> Unit
) {
    val rememberedSearchResults = remember { mutableStateOf(results) }
    rememberedSearchResults.value = results
    Log.d("ExerciseSearchResults", "results: $results") // 로그 추가

    Column {
        rememberedSearchResults.value.forEach { exercise ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedId == exercise.no,
                    onClick = { onItemSelected(exercise.no) }
                )
                Text(
                    text = exercise.name,
                    modifier = Modifier.weight(2f),
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "60 분",
                    modifier = Modifier.weight(1f),
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "${exercise.consumeKcal} kcal",
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
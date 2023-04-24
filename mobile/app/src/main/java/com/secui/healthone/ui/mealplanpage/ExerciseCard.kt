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
import com.secui.healthone.data.Exercise

@Composable
fun ExerciseCard(exercise: Exercise) {
    Card {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = exercise.name)
            Text(text = exercise.description)
            Text(text = "${exercise.caloriesBurned} kcal")
            Button(onClick = { /* 운동 기록하기 액션 */ }, modifier = Modifier.padding(8.dp)) {
                Text("수정하기")
            }
        }
    }
}
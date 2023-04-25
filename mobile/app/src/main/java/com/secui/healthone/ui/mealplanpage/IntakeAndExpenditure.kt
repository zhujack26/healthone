package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.secui.healthone.data.Exercise
import com.secui.healthone.data.Meal

@Composable
fun IntakeAndExpenditure() {
    val meals = listOf(
        Meal("아침", "된장찌개", 300, imageUrl = "https://tifyimage.s3.ap-northeast-2.amazonaws.com/006570ab-0815-45ae-acf4-d12a8e16fc3c.PNG"),
        Meal("점심", "김치찌개", 450)
    )

    val exercises = listOf(
        Exercise("Running", "30 minutes", 200),
        Exercise("Yoga", "1 hour", 250)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(if (selectedIndex == 0) Color.White else Color.Gray)
                    .clickable { selectedIndex = 0 }
                    .padding(8.dp)
            ) {
                Text("섭취 내역", color = if (selectedIndex == 0) Color.Black else Color.White)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(if (selectedIndex == 1) Color.White else Color.Gray)
                    .clickable { selectedIndex = 1 }
                    .padding(8.dp)
            ) {
                Text("소모 내역", color = if (selectedIndex == 1) Color.Black else Color.White)
            }
        }

        when (selectedIndex) {
            0 -> {
                meals.forEach { meal -> MealCard(meal) }
                Button(onClick = { /* 섭취 기록하기 */ }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)) {
                    Text("섭취 기록하기")
                }
            }
            1 -> {
                exercises.forEach { exercise -> ExerciseCard(exercise) }
                Button(onClick = { /* 운동 기록하기 */ }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)) {
                    Text("운동 기록하기")
                }
            }
        }
    }
}
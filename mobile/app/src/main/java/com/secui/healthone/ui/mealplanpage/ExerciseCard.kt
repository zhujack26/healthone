package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.data.MealPlan.ExerciseList
import com.secui.healthone.constant.AppColors

@Composable
fun ExerciseCard(exercise: ExerciseList, onDelete: (Int) -> Unit) {
    val exerciseNo = exercise.no

    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = exercise.name)
                    Text(text = "${exercise.workTime}분", fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = "${exercise.consumeCalorie} kcal")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier
                        .size(40.dp, 20.dp)
                        .background(AppColors.mono50, shape = RoundedCornerShape(percent = 50))
                        .border(BorderStroke(1.dp, AppColors.mono500), shape = RoundedCornerShape(percent = 50))
                        .clickable { onDelete(exerciseNo) }
                ) {
                    Text("삭제", color = AppColors.mono700, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

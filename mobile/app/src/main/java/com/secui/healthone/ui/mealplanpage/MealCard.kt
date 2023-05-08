package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.secui.healthone.data.MealPlan.Meal
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp
import com.secui.healthone.data.MealPlan.MealData


@Composable
<<<<<<< HEAD
fun MealCard(mealDataList: List<MealData>, name: String) {
=======
fun MealCard(mealDataList: List<MealData>, name: String, onDelete: (Int) -> Unit) {
>>>>>>> 48ed6455598a786f90b976d86faa3b9addc1deef
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = name, modifier = Modifier.align(Alignment.Start))

            Spacer(modifier = Modifier.height(8.dp))

            mealDataList.forEach { mealData ->
                val foodName = mealData.name
                val kcal = mealData.kcal
                val gram = mealData.gram

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = foodName)
                        Text(text = "${gram}g", fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "${kcal} kcal")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Box(
                        modifier = Modifier
                            .size(40.dp, 20.dp)
                            .background(Color.White, shape = RoundedCornerShape(percent = 50))
                            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(percent = 50))
<<<<<<< HEAD
                            .clickable { /* 수정 기능 */ }
                    ) {
                        Text("수정", color = Color.Black, modifier = Modifier.align(Alignment.Center))
=======
                            .clickable { onDelete(mealData.no) }
                    ) {
                        Text("삭제", color = Color.Black, modifier = Modifier.align(Alignment.Center))
>>>>>>> 48ed6455598a786f90b976d86faa3b9addc1deef
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

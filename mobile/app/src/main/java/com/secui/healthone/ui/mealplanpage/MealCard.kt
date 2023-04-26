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

@Composable
fun MealCard(mealList: List<Meal>, name: String) {
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = name, modifier = Modifier.align(Alignment.Start))

            Spacer(modifier = Modifier.height(8.dp))

            mealList.forEach { meal ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = meal.description)
                        Text(text = "Small text", fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "${meal.calories} kcal")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Box(
                        modifier = Modifier
                            .size(40.dp, 20.dp)
                            .background(Color.White, shape = RoundedCornerShape(percent = 50))
                            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(percent = 50))
                            .clickable { /* 수정 기능 */ }
                    ) {
                        Text("수정", color = Color.Black, modifier = Modifier.align(Alignment.Center))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

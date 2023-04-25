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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.secui.healthone.data.Meal
import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun MealCard(mealList: List<Meal>, name: String) {
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = name, modifier = Modifier.align(Alignment.Start))

            Spacer(modifier = Modifier.height(8.dp))

            mealList.forEach { meal ->
                Row {
                    val painter = rememberImagePainter(
                        data = meal.imageUrl,
                        builder = {
                            crossfade(true)
                            placeholder(ColorDrawable(Color.Gray.toArgb()))
                        }
                    )

                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .background(Color.Gray),
                        contentScale = ContentScale.FillBounds
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f).align(Alignment.CenterVertically)) {
                        Text(text = meal.description)
                    }

                    Column(
                        modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .size(40.dp, 20.dp)
                                .background(Color.White, shape = RoundedCornerShape(percent = 50))
                                .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(percent = 50))
                                .align(Alignment.CenterHorizontally)
                                .clickable { /* 수정 기능 */ }
                        ) {
                            Text("수정", color = Color.Black, modifier = Modifier.align(Alignment.Center))
                        }
                        Text(text = "${meal.calories} kcal")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

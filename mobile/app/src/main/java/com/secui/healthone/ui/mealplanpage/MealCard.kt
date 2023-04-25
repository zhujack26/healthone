package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

@Composable
fun MealCard(meal: Meal) {
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = meal.name, modifier = Modifier.align(Alignment.Start))

            Spacer(modifier = Modifier.height(8.dp))

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
                        .size(100.dp, 100.dp)
                        .background(Color.Gray),
                    contentScale = ContentScale.FillBounds
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(text = meal.description, modifier = Modifier.align(Alignment.Start))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${meal.calories} kcal", modifier = Modifier.align(Alignment.Start))
                }
            }
        }
    }
}

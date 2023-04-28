package com.secui.healthone.ui.datacollectpage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepGoal() {
    val stepCount = remember { mutableStateOf(6000) }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "-",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
                .clickable {
                    if (stepCount.value > 0) {
                        stepCount.value -= 500
                    }
                }
        )

//        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "${stepCount.value}걸음",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )

//        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "+",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
                .clickable { stepCount.value += 500 }
        )
    }
}
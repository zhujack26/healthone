package com.secui.healthone.ui.datacollectpage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.ui.common.AppColors

@Composable
fun StepGoal() {
    val stepCount = remember { mutableStateOf(6000) }
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,

            modifier = Modifier.fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "-",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier
                    .clickable {
                        if (stepCount.value > 0) {
                            stepCount.value -= 500
                        }
                    }
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = Color.Black
                        )
                    ) {
                        append("${stepCount.value}")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    ) {
                        append("걸음")
                    }
                }
            )
            Text(
                text = "+",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier
                    .clickable { stepCount.value += 500 }
            )
        }
    }
}
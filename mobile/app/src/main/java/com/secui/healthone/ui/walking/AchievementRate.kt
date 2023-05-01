package com.secui.healthone.ui.walking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun AchievementRate(percentage: Float, onNavigate: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("오늘 당신의 걸음 목표 달성률은?")
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append(">")
                    }
                },
                modifier = Modifier.clickable { onNavigate() }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        ProgressBar(percentage = percentage)
    }
}

@Composable
fun ProgressBar(percentage: Float) {
    val filledWidth = (percentage * 100).toInt()

    Row(
        modifier = Modifier
            .height(24.dp)
            .fillMaxWidth()
            .background(color = Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(filledWidth.dp)
                .background(color = Color.Green)
        )
    }
}
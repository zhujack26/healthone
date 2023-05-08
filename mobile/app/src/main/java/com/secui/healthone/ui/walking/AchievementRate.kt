package com.secui.healthone.ui.walking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.ui.common.AppColors

@Composable
fun AchievementRate(percentage: Float) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text ="오늘 당신의 걸음 목표 달성률은?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 24.sp, color = AppColors.black)) {
                        append(">")
                    }
                },
//                modifier = Modifier.clickable { onNavigate() }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProgressBar(percentage = percentage)
    }
}

@Composable
fun ProgressBar(percentage: Float) {
    val filledWidth = (percentage * 100).toInt()

    Box(
        modifier = Modifier
            .height(24.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(color = AppColors.mono200)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(filledWidth.dp)
                .background(color = AppColors.green600)
        )
    }
}
